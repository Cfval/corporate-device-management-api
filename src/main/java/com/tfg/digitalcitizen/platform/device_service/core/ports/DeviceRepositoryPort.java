package com.tfg.digitalcitizen.platform.device_service.core.ports;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import java.util.*;

public interface DeviceRepositoryPort {

    // Crear un nuevo dispositivo (POST /devices)
    Device save(Device device);

    // Obtener todos los dispositivos (GET /devices)
    List<Device> findAll();

    // Obtener un dispositivo por ID (GET /devices/{id})
    Optional<Device> findById(Long id);

    // Actualizar información del dispositivo (PUT /devices/{id})
    Device update(Device device);

    // Dar de baja o eliminar un dispositivo (DELETE /devices/{id})
    void deleteById(Long id);

    // Obtener dispositivos filtrados por estado (GET /devices/status/{estado})
    List<Device> findByStatus(DeviceStatus status);

    // Obtener todos los dispositivos asignados a un empleado específico
    // (GET /devices/by-employee/{employeeId})
    List<Device> findByEmployeeId(Long employeeId);
}


