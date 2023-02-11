package com.example.app.Services;

import com.example.app.config.JWTService;
import com.example.app.models.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
public class CatalogService {
    private final String URI = "https://api.spoonacular.com/recipes/complexSearch";
    private final String API_KEY = "2c5da11133cf493fa59c5b49d587e4f9";

    public String getUri() {
        return URI + "&" + API_KEY;
    }
}
