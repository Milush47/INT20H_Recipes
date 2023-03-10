package com.example.app.events.listeners;

import com.example.app.models.token.Token;
import com.example.app.models.token.TokenFactory;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import com.example.app.services.TokenService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@RequiredArgsConstructor
public abstract class EmailListener<T extends ApplicationEvent> implements ApplicationListener<T> {
    private final TokenService  tokenService;
    private final EmailService  emailService;
    private final MessageSource messageSource;

    protected abstract String getRecipient  (T event);

    protected abstract String getSubject    (T event);
    protected abstract String getUrl        (T event);
    protected abstract Locale getLocale     (T event);
    protected abstract String getMessageKey (T event);

    @Override
    public void onApplicationEvent(T event) {
        try {
            this.sendEmail(event);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void sendEmail(T event) throws MessagingException, IOException {
        String recipient    =   getRecipient(event);
        String subject      =   getSubject(event);
        String url          =   getUrl(event);

        String message = messageSource.getMessage(
                getMessageKey(event),
                null,
                getLocale(event)
                );

        String text = message + "http://localhost:5173" + url;

        emailService.sendSimpleMessage(recipient, subject, text);
    }

    protected Token createToken(User user, TokenFactory<Token> tokenTokenFactory) {
        return tokenService.createToken(user, tokenTokenFactory);
    }

}
