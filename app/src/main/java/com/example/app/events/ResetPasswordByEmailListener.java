package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import com.example.app.services.TokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPasswordByEmailListener implements ApplicationListener<ResetPasswordByEmailEvent> {
    private final TokenService tokenService;

    @Override
    public void onApplicationEvent(ResetPasswordByEmailEvent event) {

    }

    public void resetPasswordConfirmation(ResetPasswordByEmailEvent event) {
        User user = event.getUser();
    }
}
