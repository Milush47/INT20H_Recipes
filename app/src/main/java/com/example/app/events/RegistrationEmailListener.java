package com.example.app.events;

import org.springframework.context.ApplicationListener;

public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {

    }

    private void confirmRegistration(){

    }
}
