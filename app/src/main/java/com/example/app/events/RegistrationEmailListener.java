package com.example.app.events;

import com.example.app.models.entities.User;
import com.example.app.services.EmailService;
import com.example.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    private final UserService userService;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {

    }

    private void confirmRegistration(OnRegistrationSuccessEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

    }
}
