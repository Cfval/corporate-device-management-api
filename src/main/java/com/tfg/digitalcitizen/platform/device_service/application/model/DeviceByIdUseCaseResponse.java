package com.tfg.digitalcitizen.platform.device_service.application.model;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class DeviceByIdUseCaseResponse {
    private final Device device;
}

