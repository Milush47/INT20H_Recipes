package com.example.app.dto;

import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
    RegisterRequest is used for receiving data from resetPassword form
 */
@Data
public class ResetPasswordRequest {
    @Email
    @NotBlank
    private String email;
    @ValidPassword
    @NotBlank
    private String newPassword;
}
