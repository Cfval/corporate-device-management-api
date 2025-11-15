package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.GETAllDevicesUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.application.model.DevicesUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto.DevicesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GETAllDevicesRestController {

    private final GETAllDevicesUseCase useCase;

    @Operation(summary = "Get all devices")
    @ApiResponse(responseCode = "200", description = "Devices found successfully")
    @GetMapping("/devices")
    public ResponseEntity<DevicesResponse> findAll() {

        DevicesUseCaseResponse devicesResponse = useCase.invoke();

        List<DeviceDto> dtoList = devicesResponse.getDevices().stream()
                .map(DeviceMapper::toDto)
                .toList();

        return ResponseEntity.ok(
                new DevicesResponse(dtoList,
                        devicesResponse.getTotalDevices(),
                        devicesResponse.getTotalDevicesFiltered())
        );
    }
}

