package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository;

import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDeviceRepository extends JpaRepository<DeviceEntity, Long> {

    List<DeviceEntity> findByStatus(DeviceStatus status);

    List<DeviceEntity> findByEmployeeId(Long employeeId);
}

