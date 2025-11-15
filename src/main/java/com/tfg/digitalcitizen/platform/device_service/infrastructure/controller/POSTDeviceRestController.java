package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.POSTDeviceUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.exceptions.ErrorSavingDeviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class POSTDeviceRestController {

    private final POSTDeviceUseCase useCase;

    @PostMapping("/devices")
    public ResponseEntity<DeviceDto> create(@RequestBody DeviceDto dto) {
        try {
            DeviceDto saved = useCase.invoke(dto);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            throw new ErrorSavingDeviceException("Error saving device: " + e.getMessage());
        }
    }
}
