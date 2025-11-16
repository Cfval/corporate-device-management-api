package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.GETDeviceByIdUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.application.model.DeviceByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto.DeviceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GETDeviceByIdRestController {

    private final GETDeviceByIdUseCase useCase;

    @Operation(summary = "Get a device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Device found successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/devices/{id}")
    public ResponseEntity<DeviceResponse> findById(@PathVariable Long id) {

        DeviceByIdUseCaseResponse response = useCase.invoke(id);
        DeviceDto dto = DeviceMapper.toDto(response.getDevice());
        return ResponseEntity.ok(new DeviceResponse(dto));
    }
}
