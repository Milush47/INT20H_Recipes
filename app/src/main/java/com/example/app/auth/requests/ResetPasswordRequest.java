package com.example.app.auth.requests;

import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @Email
    private String email;
    @ValidPassword
    private String password;
}
