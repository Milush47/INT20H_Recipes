package com.example.app.events;

import com.example.app.models.token.ResetToken;
import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import com.example.app.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPasswordByEmailListener implements ApplicationListener<ResetPasswordByEmailEvent> {
    private final TokenService  tokenService;
    private final MessageSource messageSource;
    private final EmailService  emailService;

    @Override
    public void onApplicationEvent(ResetPasswordByEmailEvent event) {this.resetPasswordConfirmation(event);}

    public void resetPasswordConfirmation(ResetPasswordByEmailEvent event) {
        User    user        =   event.getUser();
        Token   resetToken  =   tokenService.createToken(user, ResetToken::new);

        String recipient    = user.getEmail();
        String subject      = "Reset your password";
        String url          = event.getAppUrl() +
                "/auth"             +
                "/resetPassword"    +
                "?resetToken="      +
                resetToken.getToken();

        String message = messageSource.getMessage(
                "message.resetPasswordLink",
                null,
                event.getLocale()
        );

        String text = message + "http://localhost:5173" + url;

        emailService.sendSimpleMessage(recipient, subject, text);
    }
}
