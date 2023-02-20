package com.example.app.dto;

import com.example.app.validators.fieldMatch.FieldMatch;
import com.example.app.validators.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import static com.example.app.errors.ExceptionMessage.INVALID_INPUT;

/*
    RegisterRequest is used for receiving data from registration form
*/

@FieldMatch.List(
        @FieldMatch(
                first   = "password",
                second  = "confirmedPassword",
                message = "The password fields must match"
        )
)
public record RegisterRequest(
        @NotBlank(message = "Firstname is required")
        @JsonProperty("firstname")
        String firstname,
        @NotBlank(message = "Lastname is required")
        @JsonProperty("lastname")
        String lastname,
        @Email(message = "Email must be well-formed")
        @NotBlank(message = "Email is required")
        @JsonProperty("email")
        String email,
        @ValidPassword(message = "Password must contain at least 8 characters. " +
                                 "At least one UpperCase letter, one special character(!#$*_), one digit")
        @NotBlank(message = "Password is required")
        @JsonProperty("password")
        String password,
        @NotBlank(message = "Password confirmation is required")
        @JsonProperty("confirmedPassword")
        String confirmedPassword
) {
        @Builder public RegisterRequest{}
}
