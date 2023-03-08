package com.example.app.services;

import com.example.app.dto.requests.UserRequest;
import com.example.app.dto.responses.UserResponse;
import com.example.app.errors.ExceptionMessage;
import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.models.entities.User;
import com.example.app.models.entities.VerificationToken;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final           UserRepository              userRepository;
    private final           VerificationTokenRepository verificationTokenRepository;
    private final           JWTService                  jwtService;
    private final           StorageService              storageService;
    private                 ApplicationEventPublisher   eventPublisher;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, email))
                );
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken(token, user);

        verificationTokenRepository.save(newUserToken);
    }

    public User getUserByJWT(WebRequest request) {
        String token = (
                Objects.requireNonNull(
                        request.getHeader("Authorization")
                )
        ).substring(7);

        String  email   = jwtService.extractUsername(token);

        return  userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, email)
                        )
                );
    }

    public void updateUser(
            User        user,
            UserRequest userRequest,
            WebRequest  request
    ) {
        if(!userRequest.firstname().isEmpty()) {
            user.setFirstname(userRequest.firstname());
        }

        if(!userRequest.firstname().isEmpty()) {
            user.setLastname(userRequest.lastname());
        }

        if(!userRequest.firstname().isEmpty()) {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(), appUrl));

            user.setEmail(userRequest.email());
        }

        if(!userRequest.avatar().isEmpty()) {
            storageService.uploadImage(userRequest.avatar(), user);
        }

        userRepository.save(user);
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .confirmed(user.isConfirmed())
                .preferences(user.getPreferences())
                .build();
    }
}
