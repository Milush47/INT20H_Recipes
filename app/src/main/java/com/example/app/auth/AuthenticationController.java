package com.example.app.auth;

import com.example.app.dto.AuthenticationRequest;
import com.example.app.dto.ResetPasswordRequest;
import com.example.app.dto.AuthenticationResponse;
import com.example.app.dto.RegisterRequest;
import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.models.repositories.UserRepository;
import com.example.app.dto.SuccessResponse;
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


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest registerRequest, WebRequest request
    ) throws AccessDeniedException {
        if(!emailService.isEmailExists(registerRequest.getEmail())) {
            throw new AccessDeniedException("Email is already taken");
        }



        return ResponseEntity.ok(authService.register(registerRequest, request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();

        return new ResponseEntity<>(new SuccessResponse(true), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}
