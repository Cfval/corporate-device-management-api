package com.tfg.digitalcitizen.platform.device_service.application.model;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public final class DevicesUseCaseResponse {
    private final List<Device> devices;
    private final Integer totalDevices;
    private final Integer totalDevicesFiltered;
}

