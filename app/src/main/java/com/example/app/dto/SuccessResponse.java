package com.example.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }
}
