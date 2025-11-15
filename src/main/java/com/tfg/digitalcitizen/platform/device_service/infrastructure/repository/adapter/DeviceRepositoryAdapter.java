package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.adapter;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.JpaDeviceRepository;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity.DeviceEntity;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.mappers.DeviceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DeviceRepositoryAdapter implements DeviceRepositoryPort {

    private final JpaDeviceRepository jpaRepository;

    @Override
    public Device save(Device device) {
        DeviceEntity entity = DeviceEntityMapper.toEntity(device);
        DeviceEntity saved = jpaRepository.save(entity);
        return DeviceEntityMapper.toDomain(saved);
    }

    @Override
    public List<Device> findAll() {
        return jpaRepository.findAll().stream()
                .map(DeviceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Device> findById(Long id) {
        return jpaRepository.findById(id)
                .map(DeviceEntityMapper::toDomain);
    }

    @Override
    public Device update(Device device) {
        DeviceEntity entity = DeviceEntityMapper.toEntity(device);
        DeviceEntity updated = jpaRepository.save(entity);
        return DeviceEntityMapper.toDomain(updated);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Device> findByStatus(DeviceStatus status) {
        return jpaRepository.findByStatus(status).stream()
                .map(DeviceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Device> findByEmployeeId(Long employeeId) {
        return jpaRepository.findByEmployeeId(employeeId).stream()
                .map(DeviceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}

