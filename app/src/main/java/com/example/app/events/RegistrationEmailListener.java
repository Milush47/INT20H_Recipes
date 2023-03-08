package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.token.VerificationToken;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import com.example.app.services.TokenService;
import com.example.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    private final TokenService  tokenService;
    private final EmailService  emailService;
    private final MessageSource messageSource;

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event) {
        User    user                = event.getUser();
        Token   verificationToken   = tokenService.createToken(user, VerificationToken::new);

        String recipient    = user.getEmail();
        String subject      = "Registration confirmation";
        String url          = event.getAppUrl() +
                "/auth"                 +
                "/confirmRegistration"  +
                "?verificationToken="   +
                verificationToken.getToken();

        String message = messageSource.getMessage(
                "message.registrationSuccessConfirmationLink",
                null,
                event.getLocale()
        );

        String text = message + "http://localhost:5173" + url;

        emailService.sendRegistrationConfirmation(recipient, subject, text);
    }
}
