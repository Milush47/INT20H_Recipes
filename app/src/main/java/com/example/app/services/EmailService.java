package com.example.app.services;

import com.example.app.dto.responses.SuccessResponse;
import com.example.app.dto.responses.UserResponse;
import com.example.app.errors.InvalidVerificationTokenException;
import com.example.app.models.entities.User;
import com.example.app.models.entities.VerificationToken;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender                mailSender;
    private final UserRepository                userRepository;
    private final VerificationTokenRepository   verificationTokenRepository;
    private final UserService                   userService;

    // Method is used for verification email on registration step
    public void sendRegistrationConfirmation(String recipient, String subject, String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    public UserResponse completeVerification(String token, User user) {
        if(isVerificationTokenValid(token, user)) {
            user.setConfirmed(true);

            userRepository.save(user);

            return userService.mapToUserResponse(user);
        }

        throw new InvalidVerificationTokenException("Verification token is invalid");
    }

    private boolean isVerificationTokenValid(
            String verificationToken,
            User user
    ) throws InvalidVerificationTokenException {
        VerificationToken verificationTokenEntity = verificationTokenRepository
                .findByToken(verificationToken)
                .orElseThrow(() -> new InvalidVerificationTokenException("Verification token is not found"));

        if(verificationTokenEntity.isExpired()) {
            throw new InvalidVerificationTokenException("Verification token is expired");
        }

        return (verificationTokenEntity.getUser())
                .equals(user);
    }
}
