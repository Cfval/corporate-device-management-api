package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DELETEDeviceUseCase {

    private final DeviceRepositoryPort repository;

    public Long invoke(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Device ID cannot be null");
        }

        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device with ID " + id + " not found"));

        repository.deleteById(id);
        return id;
    }
}

