package com.tfg.digitalcitizen.platform.shared.api;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiDeleteResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String path;

    private final Long id;
    private final String message;

    public static ApiDeleteResponse of(Long id, String message, int status, String path) {
        return ApiDeleteResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .path(path)
                .id(id)
                .message(message)
                .build();
    }
}

