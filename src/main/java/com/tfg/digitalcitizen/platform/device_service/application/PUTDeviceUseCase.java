package com.tfg.digitalcitizen.platform.device_service.application;

import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceType;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PUTDeviceUseCase {

    private final DeviceRepositoryPort repository;

    public DeviceDto invoke(Long id, DeviceDto deviceDto) {
        if (deviceDto == null) {
            throw new IllegalArgumentException("Device data cannot be null");
        }

        Device existingDevice = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found with ID: " + id));

        DeviceStatus deviceStatus;
        DeviceType deviceType;

        try {
            deviceType = DeviceType.valueOf(deviceDto.getType().toUpperCase());
            deviceStatus = DeviceStatus.valueOf(deviceDto.getStatus().toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid device type or status: " + deviceDto.getType() + deviceDto.getStatus());
        }

        Device updatedDevice = existingDevice.updateData(
                deviceType,
                deviceDto.getImei(),
                deviceDto.getBrand(),
                deviceDto.getModel(),
                deviceDto.getSerialNumber(),
                deviceDto.getOs(),
                deviceStatus,
                deviceDto.getClientId(),
                deviceDto.getLineId(),
                deviceDto.getEmployeeId()
        );

        Device savedDevice = repository.update(updatedDevice);
        return DeviceMapper.toDto(savedDevice);
    }
}

