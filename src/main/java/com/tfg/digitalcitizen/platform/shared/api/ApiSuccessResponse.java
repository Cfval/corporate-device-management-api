package com.tfg.digitalcitizen.platform.shared.api;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiSuccessResponse<T> {

    private final LocalDateTime timestamp;
    private final int status;
    private final String path;
    private final T data;

    public static <T> ApiSuccessResponse<T> of(T data, int status, String path) {
        return ApiSuccessResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .path(path)
                .data(data)
                .build();
    }
}
