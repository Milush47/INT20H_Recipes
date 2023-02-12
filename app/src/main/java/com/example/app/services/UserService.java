package com.example.app.services;

import com.example.app.models.entities.User;
import com.example.app.models.entities.VerificationToken;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with %s email not found";
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken(token, user);
        verificationTokenRepository.save(newUserToken);
    }
}
