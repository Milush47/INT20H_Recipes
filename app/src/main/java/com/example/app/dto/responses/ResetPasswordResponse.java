package com.example.app.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record ResetPasswordResponse(
        @JsonProperty("resetToken")
        String resetToken
) {
    @Builder public ResetPasswordResponse {}
}
