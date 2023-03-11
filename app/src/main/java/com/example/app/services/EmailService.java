package com.example.app.services;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService{
    private         final   JavaMailSender      mailSender;
    private         final   UserService         userService;
    private static          Mustache            mustache;

    static {
        MustacheFactory mf = new DefaultMustacheFactory();
        mustache = mf.compile("email-template.mustache");
    }

    @Async
    public void sendMessage(
            String recipient,
            String subject,
            Map<String, String> text
    ) throws MessagingException, IOException, ServletException {

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

    private String processTemplate(Map<String, String> text) throws ServletException, IOException {

        Map<String, Object> data = new HashMap<>();
        data.put("name", text.get("userName"));
        data.put("buttonLink", text.get("url"));
        data.put("message", text.get("message"));
        data.put("subject", text.get("subject"));

        StringWriter stringWriter = new StringWriter();
        mustache.execute(new PrintWriter(stringWriter), data).flush();
        return stringWriter.toString();
    }
}
