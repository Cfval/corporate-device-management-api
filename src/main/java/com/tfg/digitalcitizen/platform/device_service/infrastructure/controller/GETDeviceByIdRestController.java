package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.GETDeviceByIdUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.application.model.DeviceByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto.DeviceResponse;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.exceptions.DeviceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GETDeviceByIdRestController {

    private final GETDeviceByIdUseCase useCase;

    @GetMapping("/devices/{id}")
    public ResponseEntity<DeviceResponse> findById(@PathVariable Long id) {

        DeviceByIdUseCaseResponse response = useCase.invoke(id);
        if (response.getDevice() == null) {
            throw new DeviceNotFoundException("Device not found with ID: " + id);
        }

        DeviceDto dto = DeviceMapper.toDto(response.getDevice());
        return ResponseEntity.ok(new DeviceResponse(dto));
    }
}
