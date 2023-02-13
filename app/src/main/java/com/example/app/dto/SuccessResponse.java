package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;

/*
    SuccessResponse is used for sending responses to frontend with piece of information
 */
@Data
@AllArgsConstructor
public class SuccessResponse {
    private String  message;
    private boolean success;
    private Object  data;

    public SuccessResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public boolean  isSuccess() {
        return success;
    }
}
