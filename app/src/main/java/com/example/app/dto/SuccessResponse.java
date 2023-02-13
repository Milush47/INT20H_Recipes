package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
    SuccessResponse is used for sending responses to frontend with piece of information
 */
@Data
@AllArgsConstructor
public class SuccessResponse {
    private boolean success;
    private String  message;

    public boolean  isSuccess() {
        return success;
    }
}
