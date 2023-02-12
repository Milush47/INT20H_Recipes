package com.example.app.controllers;

import com.example.app.services.CatalogService;
import com.example.app.services.JWTService;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController("/catalog")
public class CatalogController {
    private final UserRepository    userRepository;
    private final JWTService        jwtService;
    private final CatalogService    catalogService;
    private final RestTemplate      restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<String> getAllRecipes(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwtToken = authorization.substring(7);
            String username = jwtService.extractUsername(jwtToken);
            User user = userRepository.findByEmail(username).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );

            return ResponseEntity.ok("ok");
        } else {
            String result = restTemplate.getForObject(catalogService.getUri(), String.class);

            return ResponseEntity.ok(result);
        }
    }
}
