package com.tfg.digitalcitizen.platform.device_service.infrastructure.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}

