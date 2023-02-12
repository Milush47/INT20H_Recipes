package com.example.app.services;

import com.example.app.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    public void sendPasswordResetEmail(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset");
        message.setText("Your new password is: " + password);
        mailSender.send(message);
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
