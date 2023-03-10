package com.example.app.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class EmailService{
    private final JavaMailSender                mailSender;
    private final UserService                   userService;

    // Method is used for verification email on registration step

    @Async
    public void sendSimpleMessage(
            String recipient,
            String subject,
            String text
    ) throws MessagingException, IOException {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("my-email-template.html");

        assert inputStream != null;

        String content = MessageFormat.format(
                new String(inputStream.readAllBytes()),
                text
        );

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(content, true);


        mailSender.send(message);
    }

    public boolean isEmailExists(String email) {
        return userService.isPresent(email);
    }
}
