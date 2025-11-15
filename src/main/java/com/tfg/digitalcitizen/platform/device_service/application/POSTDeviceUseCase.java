package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class POSTDeviceUseCase {

    private final DeviceRepositoryPort repository;

    public DeviceDto invoke(DeviceDto deviceDto) {
        if (deviceDto == null) {
            throw new IllegalArgumentException("Device data cannot be null");
        }

        Device deviceToSave = DeviceMapper.toDomain(deviceDto);
        Device savedDevice = repository.save(deviceToSave);
        return DeviceMapper.toDto(savedDevice);
    }
}

