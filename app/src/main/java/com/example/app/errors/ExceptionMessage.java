package com.example.app.errors;


import java.util.Map;


public class ExceptionMessage {
    public static final String              USER_NOT_FOUND  = "User with %s email not found";
    public static final String              EMAIl_IS_TAKEN  = "Email %s is already taken";

    public static final Map<String, String> INVALID_INPUT   = Map.of(
            "email_valid",      "Email must be well-formed",
            "email_is_req",     "Email is required",
            "fn_is_req",        "Firstname is required",
            "ln_is_req",        "Lastname is required",
            "pswd_conf_is_req", "Password confirmation is required",
            "pswd_is_req",      "Password is required",
            "pswd_valid",       "Password must contain at least 8 characters. " +
                                        "At least one UpperCase letter, one special character(!#$*_), one digit",
            "pswds_must_match", "The password fields must match"
    );
}
