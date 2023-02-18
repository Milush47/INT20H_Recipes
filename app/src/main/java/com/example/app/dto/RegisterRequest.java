package com.example.app.dto;

import com.example.app.validators.fieldMatch.FieldMatch;
import com.example.app.validators.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

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
        @NotBlank(message = "${INVALID_INPUT.fn_is_req}")
        @JsonProperty("firstname")
        String firstname,
        @NotBlank(message = "${INVALID_INPUT.ln_is_req}")
        @JsonProperty("lastname")
        String lastname,
        @Email(message = "{email_valid}")
        @NotBlank(message = "${INVALID_INPUT.email_is_req}")
        @JsonProperty("email")
        String email,
        @ValidPassword(message = "${INVALID_INPUT.pswd_valid}")
        @NotBlank(message = "${INVALID_INPUT.pswd_is_req}")
        @JsonProperty("password")
        String password,
        @NotBlank(message = "${INVALID_INPUT.pswd_conf_is_req}")
        @JsonProperty("confirmedPassword")
        String confirmedPassword
) {
        @Builder public RegisterRequest {}
}
