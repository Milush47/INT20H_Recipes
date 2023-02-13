package com.example.app.services;

import org.springframework.stereotype.Service;


@Service
public class CatalogService {
    private final String URI        = "https://api.spoonacular.com/recipes/complexSearch";
    private final String API_KEY    = "2c5da11133cf493fa59c5b49d587e4f9";

    public String getUri() {
        return URI + "&" + API_KEY;
    }
}
