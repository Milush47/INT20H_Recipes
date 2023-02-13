package com.example.app.dto;

import com.example.app.validators.fieldMatch.FieldMatch;
import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    AuthenticationRequest is used for receiving data from authentication form
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldMatch.List(
        @FieldMatch(
                first   = "password",
                second  = "confirmedPassword",
                message = "The password fields must match"
        )
)
public class AuthenticationRequest {
    @Email
    @NotBlank
    private String email;
    @ValidPassword
    @NotBlank
    private String password;
    @NotBlank
    private String confirmedPassword;
}
