package com.tfg.digitalcitizen.platform.device_service.core.model;

import java.time.LocalDate;

public final class Device {

    private Long id;
    private final DeviceType type;            // tipo de dispositivo (SMARTPHONE, TABLET, LAPTOP, ROUTER, OTHER)
    private final Imei imei;                  // puede ser null si no aplica
    private final Brand brand;                // puede ser null si no aplica
    private final Model model;                // puede ser null si no aplica
    private final SerialNumber serialNumber;  // puede ser null si no aplica
    private final OperatingSystem os;        // puede ser null si no aplica
    private final DeviceStatus status;
    private final LocalDate activationDate;
    private final Long clientId;
    private final Long lineId;
    private final Long employeeId;

    // --- Getters ---
    public Long id() { return id; }
    public String type() { return type.name(); }
    public String imei() { return imei.toPrimitive(); }
    public String brand() { return brand.toPrimitive(); }
    public String model() { return model.toPrimitive(); }
    public String serialNumber() { return serialNumber.toPrimitive(); }
    public String os() { return os.toPrimitive(); }
    public String status() { return status.name(); }
    public LocalDate activationDate() { return activationDate; }
    public Long clientId() { return clientId; }
    public Long lineId() { return lineId; }
    public Long employeeId() { return employeeId; }

    // --- Constructor privado ---
    private Device(Long id, DeviceType type, String imei, String brand, String model,
                   String serialNumber, String os, DeviceStatus status, LocalDate activationDate,
                   Long clientId, Long lineId, Long employeeId) {

        if (type == null || status == null || activationDate == null || clientId == null) {
            throw new IllegalArgumentException("Mandatory fields cannot be null.");
        }

        this.id = id;
        this.type = type;
        this.imei = Imei.fromPrimitive(imei);
        this.brand = Brand.fromPrimitive(brand);
        this.model = Model.fromPrimitive(model);
        this.serialNumber = SerialNumber.fromPrimitive(serialNumber);
        this.os = OperatingSystem.fromPrimitive(os);
        this.status = status;
        this.activationDate = activationDate;
        this.clientId = clientId;
        this.lineId = lineId;
        this.employeeId = employeeId;
    }

    // --- Factory methods ---
    public static Device fromPrimitives(DeviceType type, String imei, String brand, String model,
                                        String serialNumber, String os, DeviceStatus status,
                                        LocalDate activationDate, Long clientId, Long lineId, Long employeeId) {
        return new Device(null, type, imei, brand, model, serialNumber, os, status, activationDate,
                clientId, lineId, employeeId);
    }

    public static Device fromPrimitives(Long id, DeviceType type, String imei, String brand, String model,
                                        String serialNumber, String os, DeviceStatus status,
                                        LocalDate activationDate, Long clientId, Long lineId, Long employeeId) {
        return new Device(id, type, imei, brand, model, serialNumber, os, status, activationDate,
                clientId, lineId, employeeId);
    }

    // --- Business logic ---
    public boolean isActive() {
        return this.status == DeviceStatus.ASSIGNED || this.status == DeviceStatus.STORAGE;
    }

    public Device markAs(DeviceStatus newStatus) {
        return new Device(this.id, this.type, this.imei.toPrimitive(), this.brand.toPrimitive(),
                this.model.toPrimitive(), this.serialNumber.toPrimitive(), this.os.toPrimitive(),
                newStatus, this.activationDate, this.clientId, this.lineId, this.employeeId);
    }

    public Device assignTo(Long lineId, Long employeeId) {
        return new Device(this.id, this.type, this.imei.toPrimitive(), this.brand.toPrimitive(),
                this.model.toPrimitive(), this.serialNumber.toPrimitive(), this.os.toPrimitive(),
                DeviceStatus.ASSIGNED, this.activationDate, this.clientId, lineId, employeeId);
    }

    public Device unassign() {
        return new Device(this.id, this.type, this.imei.toPrimitive(), this.brand.toPrimitive(),
                this.model.toPrimitive(), this.serialNumber.toPrimitive(), this.os.toPrimitive(),
                DeviceStatus.STORAGE, this.activationDate, this.clientId, null, null);
    }

    public Device updateData(DeviceType type, String imei, String brand, String model,
                             String serialNumber, String os, DeviceStatus status, Long clientId, Long lineId, Long employeeId) {
        return new Device(
                this.id, // mantiene el ID original
                type,
                imei,
                brand,
                model,
                serialNumber,
                os,
                status,
                this.activationDate, // mantiene la fecha de activación original
                clientId,
                lineId,
                employeeId
        );
    }
}



