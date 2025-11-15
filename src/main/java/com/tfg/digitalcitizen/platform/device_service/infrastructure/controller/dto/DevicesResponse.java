package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto;

import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DevicesResponse {
    private final List<DeviceDto> devices;
    private final Integer totalDevices;
    private final Integer totalDevicesFiltered;
}

