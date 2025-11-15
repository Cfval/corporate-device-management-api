package com.tfg.digitalcitizen.platform.device_service.application.mapper;

import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceType;

import java.util.List;
import java.util.stream.Collectors;

public final class DeviceMapper {

    private DeviceMapper() {}

    public static DeviceDto toDto(Device device) {
        return new DeviceDto(
                device.id(),
                device.type(),
                device.imei(),
                device.brand(),
                device.model(),
                device.serialNumber(),
                device.os(),
                device.status(),
                device.activationDate(),
                device.clientId(),
                device.lineId(),
                device.employeeId()
        );
    }

    public static List<DeviceDto> toDtoList(List<Device> devices) {
        return devices.stream()
                .map(DeviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Device toDomain(DeviceDto dto) {
        return Device.fromPrimitives(
                dto.getId(),
                DeviceType.valueOf(dto.getType().toUpperCase()),
                dto.getImei(),
                dto.getBrand(),
                dto.getModel(),
                dto.getSerialNumber(),
                dto.getOs(),
                DeviceStatus.valueOf(dto.getStatus().toUpperCase()),
                dto.getActivationDate(),
                dto.getClientId(),
                dto.getLineId(),
                dto.getEmployeeId()
        );
    }
}

