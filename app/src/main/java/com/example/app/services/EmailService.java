package com.example.app.services;

import com.sun.jdi.InternalException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EmailService{
    private         final JavaMailSender    mailSender;
    private         final UserService       userService;
    private static  final String            TEMPLATE;

    static {
        try (
                InputStream inputStream = EmailService.class
                        .getClassLoader()
                        .getResourceAsStream("my-email-template.html")
        ) {
            assert inputStream != null;

            TEMPLATE = new String(inputStream.readAllBytes());

        } catch (IOException ex) {
            throw new InternalException(
                    ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
        }
    }

    @Async
    public void sendMessage(
            String recipient,
            String subject,
            String text
    ) throws MessagingException {

        MimeMessage         message = mailSender.createMimeMessage();
        MimeMessageHelper   helper  = new MimeMessageHelper(message, true);

        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(processTemplate(text), true);

        mailSender.send(message);
    }

    public boolean isEmailExists(String email) {
        return userService.isPresent(email);
    }

    private String processTemplate(String text) {
        return MessageFormat.format(TEMPLATE, text);
    }
}
