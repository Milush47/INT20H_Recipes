package com.example.app.dto;

import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record AuthenticationRequest(
        @Email(message = "${INVALID_INPUT.email_valid}")
        @NotBlank(message = "${INVALID_INPUT.email_is_req}")
        String email,
        @ValidPassword(message = "${INVALID_INPUT.pswd_valid}")
        @NotBlank(message = "${INVALID_INPUT.pswd_is_req}")
        String password
) {
        @Builder public AuthenticationRequest {}
}
