package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.PUTDeviceUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.exceptions.DeviceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PUTDeviceRestController {

    private final PUTDeviceUseCase useCase;

    @PutMapping("/devices/{id}")
    public ResponseEntity<DeviceDto> update(@PathVariable Long id, @RequestBody DeviceDto dto) {

        DeviceDto updated = useCase.invoke(id, dto);
        if (updated == null) {
            throw new DeviceNotFoundException("Device not found with ID: " + id);
        }

        return ResponseEntity.ok(updated);
    }
}

