package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.DELETEDeviceUseCase;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.exceptions.DeviceDeletionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DELETEDeviceRestController {

    private final DELETEDeviceUseCase useCase;

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            useCase.invoke(id);
            return ResponseEntity.ok("Device deleted successfully.");
        } catch (Exception e) {
            throw new DeviceDeletionException("Error deleting device: " + e.getMessage());
        }
    }
}
