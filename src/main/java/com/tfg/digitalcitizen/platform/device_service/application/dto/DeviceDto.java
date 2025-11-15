package com.tfg.digitalcitizen.platform.device_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class DeviceDto {
    private final Long id;
    private final String type;
    private final String imei;
    private final String brand;
    private final String model;
    private final String serialNumber;
    private final String os;
    private final String status;
    private final LocalDate activationDate;
    private final Long clientId;
    private final Long lineId;
    private final Long employeeId;
}
