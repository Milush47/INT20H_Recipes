package com.example.app.dto;

import lombok.Builder;

public record UserResponse(
        String firstname,
        String lastname,
        String email,
        String preferences

) {
    @Builder public UserResponse {}
}
