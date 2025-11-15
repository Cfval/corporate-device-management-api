package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.application.model.DevicesUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GETDevicesByStatusUseCase {

    private final DeviceRepositoryPort repository;

    public DevicesUseCaseResponse invoke(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        DeviceStatus deviceStatus;
        try {
            deviceStatus = DeviceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        List<Device> devices = repository.findByStatus(deviceStatus);
        Integer totalDevices = devices.size();
        Integer totalDevicesFiltered = devices.size(); // TODO: implementar filtros

        return new DevicesUseCaseResponse(devices, totalDevices, totalDevicesFiltered);
    }
}
