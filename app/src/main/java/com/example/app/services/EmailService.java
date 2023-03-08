package com.example.app.services;

import com.example.app.dto.responses.UserResponse;
import com.example.app.errors.InvalidTokenException;
import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender                mailSender;
    private final TokenService                  tokenService;
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
        return userService.isPresent(email);
    }


}
