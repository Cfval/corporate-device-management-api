package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.application.model.DevicesUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GETAllDevicesUseCase {

    private final DeviceRepositoryPort repository;

    public DevicesUseCaseResponse invoke() {

        List<Device> devices = repository.findAll();
        Integer totalDevices = devices.size();
        Integer totalDevicesFiltered = devices.size(); // TODO: implementar filtros

        return new DevicesUseCaseResponse(devices, totalDevices, totalDevicesFiltered);
    }
}

