package com.example.app.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
}
