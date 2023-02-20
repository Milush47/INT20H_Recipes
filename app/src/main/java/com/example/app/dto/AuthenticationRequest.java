package com.example.app.dto;

import com.example.app.errors.ExceptionMessage;
import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record AuthenticationRequest(
        @Email(message = "Email must be well-formed")
        @NotBlank(message = "Email is required")
        String email,
        @ValidPassword(message ="Password must contain at least 8 characters. " +
                                "At least one UpperCase letter, one special character(!#$*_), one digit")
        @NotBlank(message = "Password is required")
        String password
) {
        @Builder public AuthenticationRequest {}
}
