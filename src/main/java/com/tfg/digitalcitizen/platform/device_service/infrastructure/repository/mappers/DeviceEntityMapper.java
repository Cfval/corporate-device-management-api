package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.mappers;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceType;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity.DeviceEntity;

public final class DeviceEntityMapper {

    private DeviceEntityMapper() {}


    // Domain → Entity
    public static DeviceEntity toEntity(Device device) {
        return new DeviceEntity(
                device.id(),
                DeviceType.valueOf(device.type().toUpperCase()),   // ENUM real
                device.imei(),
                device.brand(),
                device.model(),
                device.serialNumber(),
                device.os(),
                DeviceStatus.valueOf(device.status().toUpperCase()), // ENUM real
                device.activationDate(),
                device.clientId(),
                device.lineId(),
                device.employeeId()
        );
    }

    // Entity → Domain
    public static Device toDomain(DeviceEntity entity) {
        return Device.fromPrimitives(
                entity.getId(),
                entity.getType(),             // DeviceType ENUM
                entity.getImei(),
                entity.getBrand(),
                entity.getModel(),
                entity.getSerialNumber(),
                entity.getOs(),
                entity.getStatus(),           // DeviceStatus ENUM
                entity.getActivationDate(),
                entity.getClientId(),
                entity.getLineId(),
                entity.getEmployeeId()
        );
    }
}

