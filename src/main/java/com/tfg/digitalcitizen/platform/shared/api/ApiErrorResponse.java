package com.tfg.digitalcitizen.platform.shared.api;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String path;
    private final String message;

    public static ApiErrorResponse of(String message, int status, String path) {
        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .path(path)
                .message(message)
                .build();
    }
}

