package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto;

import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceResponse {
    private final DeviceDto device;
}

