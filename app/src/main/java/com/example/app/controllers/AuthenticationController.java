package com.example.app.controllers;

import com.example.app.dto.AuthenticationRequest;
import com.example.app.dto.ResetPasswordRequest;
import com.example.app.dto.AuthenticationResponse;
import com.example.app.dto.RegisterRequest;
import com.example.app.dto.SuccessResponse;
import com.example.app.errors.ExceptionMessage;
import com.example.app.services.AuthenticationService;
import com.example.app.services.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

/*
    AuthController is made for mapping following paths:
        - auth/register         (POST)
        - auth/authenticate     (POST)
        - auth/logout           (POST)
        - auth/reset-password   (POST)
    It is responsible for all actions with User on web side
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    // Autowired variables using constructor
    private final AuthenticationService authService;
    private final EmailService          emailService;

    // responsible for registration new user.
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            WebRequest request
    ) throws AccessDeniedException {

        String email = registerRequest.getEmail();

        if(!emailService.isEmailExists(email)) {
            throw new AccessDeniedException(
                    String.format(ExceptionMessage.EMAIl_IS_TAKEN, email)
            );
        }

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is registered")
                        .success(true)
                        .data(authService.register(registerRequest, request))
                        .build()
        );
    }

    // responsible for authentication user (by jwt)
    @PostMapping("/authenticate")
    public ResponseEntity<SuccessResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is authenticated")
                        .success(true)
                        .data(authService.authenticate(request))
                        .build()
        );
    }

    // responsible for log outing user
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is logged out")
                        .success(true)
                        .build()
        );
    }

    // responsible for reset user's password (new password is provided by user)
    @PostMapping("/reset-password")
    public ResponseEntity<SuccessResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Password is reset")
                        .success(true)
                        .data(authService.resetPassword(request))
                        .build()
        );
    }
}
