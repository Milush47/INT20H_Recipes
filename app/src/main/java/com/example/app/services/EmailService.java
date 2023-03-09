package com.example.app.services;

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
    public void sendSimpleMessage(String recipient, String subject, String text){
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
