package com.example.app.validators.response;

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
