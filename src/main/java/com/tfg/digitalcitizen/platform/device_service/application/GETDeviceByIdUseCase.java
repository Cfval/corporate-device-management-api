package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.application.model.DeviceByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GETDeviceByIdUseCase {

    private final DeviceRepositoryPort repository;

    public DeviceByIdUseCaseResponse invoke(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Device device = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device not found with ID: " + id));

        return new DeviceByIdUseCaseResponse(device);
    }
}