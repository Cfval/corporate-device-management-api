package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.PUTDeviceUseCase;
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
public class PUTDeviceRestController {

    private final PUTDeviceUseCase useCase;

    @Operation(summary = "Update device information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device updated successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @PutMapping("/devices/{id}")
    public ResponseEntity<ApiSuccessResponse<DeviceDto>> update(@PathVariable Long id, @RequestBody DeviceDto dto,
                                                                HttpServletRequest request) {

        DeviceDto updated = useCase.invoke(id, dto);

        return ResponseEntity.ok(ApiSuccessResponse.of(updated, 200, request.getRequestURI()));
    }
}

