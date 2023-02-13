package com.example.app.controllers;

import com.example.app.dto.AuthenticationRequest;
import com.example.app.dto.ResetPasswordRequest;
import com.example.app.dto.AuthenticationResponse;
import com.example.app.dto.RegisterRequest;
import com.example.app.dto.SuccessResponse;
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
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            WebRequest request
    ) throws AccessDeniedException {

        if(!emailService.isEmailExists(registerRequest.getEmail())) {
            throw new AccessDeniedException("Email is already taken");
        }
        return ResponseEntity.ok(authService.register(registerRequest, request));
    }

    // responsible for authentication user (by jwt)
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // responsible for log outing user
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();

        return new ResponseEntity<>(
                new SuccessResponse( "User was logged out", true),
                HttpStatus.OK
        );
    }

    // responsible for reset user's password (new password is provided by user)
    @PostMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
