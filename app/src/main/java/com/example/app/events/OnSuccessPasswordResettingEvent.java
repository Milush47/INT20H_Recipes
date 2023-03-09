package com.example.app.events;

import com.example.app.models.user.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnSuccessPasswordResettingEvent extends ApplicationEvent {
    private static final    Long    serialVersionUID = 1L;
    private                 String  appUrl;
    private Locale locale;
    private User user;

    public OnSuccessPasswordResettingEvent(
            User    user,
            Locale  locale,
            String  appUrl
    ) {
        super(user);

        this.user   = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
