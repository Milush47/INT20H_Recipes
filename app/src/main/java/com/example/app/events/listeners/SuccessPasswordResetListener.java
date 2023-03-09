package com.example.app.events.listeners;

import com.example.app.events.OnSuccessPasswordResettingEvent;
import com.example.app.events.ResetPasswordByEmailEvent;
import com.example.app.models.token.ResetToken;
import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuccessPasswordResetListener implements ApplicationListener<OnSuccessPasswordResettingEvent> {
    private final MessageSource messageSource;
    private final EmailService  emailService;
    @Override
    public void onApplicationEvent(OnSuccessPasswordResettingEvent event) {
        this.passwordSuccessfullyReset(event);
    }

    public void passwordSuccessfullyReset(OnSuccessPasswordResettingEvent event) {
        User user        =   event.getUser();

        String recipient    = user.getEmail();
        String subject      = "Password successfully reset";
        String url          = event.getAppUrl() +
                "/profile";

        String message = messageSource.getMessage(
                "message.passwordSuccessfullyReset",
                null,
                event.getLocale()
        );

        String text = message + "http://localhost:5173" + url;

        emailService.sendSimpleMessage(recipient, subject, text);
    }
}
