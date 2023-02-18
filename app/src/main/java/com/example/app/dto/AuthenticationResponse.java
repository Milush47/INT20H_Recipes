package com.example.app.dto;

import lombok.Builder;

/*
    AuthenticationResponse is used for sending response when user has just authorized
 */
public record AuthenticationResponse(
        String token
) {
    @Builder public AuthenticationResponse {}
}
