package com.example.app.dto.requests;

import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
    RegisterRequest is used for receiving data from resetPassword form
 */
public record ResetPasswordRequest(
        @Email(message = "email.valid")
        @NotBlank(message = "email.is.req")
        String email,
        @ValidPassword(message = "pswd.valid")
        @NotBlank(message = "pswd.is.req")
        String newPassword
) {}
