package com.example.app.events;

import com.example.app.models.entities.User;
import com.example.app.services.EmailService;
import com.example.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    private final UserService   userService;
    private final EmailService  emailService;
    private final MessageSource messageSource;

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationSuccessEvent event) {
        User    user        = event.getUser();
        String  token       = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        String recipient    = user.getEmail();
        String subject      = "Registration confirmation";
        String url          = event.getAppUrl() + "/auth" + "/confirmRegistration?token=" + token;

        System.out.println(event.getLocale());

        String message = messageSource.getMessage(
                "message.registrationSuccessConfirmationLink",
                null,
                event.getLocale()
        );

        String text = message + "http://localhost:5173" + url;

        emailService.sendRegistrationConfirmation(recipient, subject, text);
    }
}
