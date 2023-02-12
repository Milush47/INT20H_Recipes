package com.example.app.events;

import com.example.app.models.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationSuccessEvent extends ApplicationEvent {
    private static final Long serialVersionUID = 1L;
    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationSuccessEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
