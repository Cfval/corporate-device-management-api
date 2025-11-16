package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.POSTDeviceUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.shared.api.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class POSTDeviceRestController {

    private final POSTDeviceUseCase useCase;

    @Operation(summary = "Create a new device")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Device created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid device data")
    })
    @PostMapping("/devices")
    public ResponseEntity<ApiSuccessResponse<DeviceDto>> create(@RequestBody DeviceDto dto, HttpServletRequest request) {

        DeviceDto saved = useCase.invoke(dto);

        return ResponseEntity.status(201).body(ApiSuccessResponse.of(saved, 201, request.getRequestURI()));
    }
}
