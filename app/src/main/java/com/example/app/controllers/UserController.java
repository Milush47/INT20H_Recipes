package com.example.app.controllers;

import com.example.app.dto.SuccessResponse;
import com.example.app.dto.UserResponse;
import com.example.app.errors.ExceptionMessage;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import com.example.app.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final JWTService        jwtService;
    private final UserRepository    userRepository;
    @GetMapping
    public ResponseEntity<SuccessResponse> getUser(WebRequest request) {
        String token = (
                Objects.requireNonNull(
                        request.getHeader("Authorization")
                )
        ).substring(7);

        String  email   = jwtService.extractUsername(token);

        User    user    = userRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new UsernameNotFoundException(
                                    String.format(ExceptionMessage.USER_NOT_FOUND, email)
                            )
                    );

        UserResponse userResponse = UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .preferences(user.getPreferences())
                .build();

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User details are provided")
                        .success(true)
                        .data(userResponse)
                        .build()
        );
    }
}
