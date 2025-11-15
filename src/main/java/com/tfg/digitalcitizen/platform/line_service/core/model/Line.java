package com.tfg.digitalcitizen.platform.line_service.core.model;

import com.tfg.digitalcitizen.platform.line_service.core.model.simcard.SIMCard;
import java.time.LocalDate;

public final class Line {

    private Long id;
    private final PhoneNumber phoneNumber;
    private final TariffType tariffType;
    private final LocalDate activationDate;
    private final SIMCard simCard;     // composición: una SIM activa
    private final LineStatus status;
    private final Long clientId;
    private final Long employeeId;
    private final Long deviceId;

    // --- Getters ---
    public Long id() { return id; }
    public String phoneNumber() { return phoneNumber.toPrimitive(); }
    public String tariffType() { return tariffType.toPrimitive(); }
    public LocalDate activationDate() { return activationDate; }
    public SIMCard simCard() { return simCard; }
    public String status() { return status.name(); }
    public Long clientId() { return clientId; }
    public Long employeeId() { return employeeId; }
    public Long deviceId() { return deviceId; }

    // --- Constructor privado ---
    private Line(Long id, String phoneNumber, String tariffType, LocalDate activationDate,
                 SIMCard simCard, LineStatus status, Long clientId, Long employeeId, Long deviceId) {

        if (phoneNumber == null || tariffType == null || activationDate == null ||
                simCard == null || status == null || clientId == null) {
            throw new IllegalArgumentException("Mandatory fields cannot be null.");
        }

        this.id = id;
        this.phoneNumber = PhoneNumber.fromPrimitive(phoneNumber);
        this.tariffType = TariffType.fromPrimitive(tariffType);
        this.activationDate = activationDate;
        this.simCard = simCard;
        this.status = status;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.deviceId = deviceId;
    }

    // --- Factory methods ---
    public static Line fromPrimitives(String phoneNumber, String tariffType, LocalDate activationDate,
                                      SIMCard simCard, LineStatus status,
                                      Long clientId, Long employeeId, Long deviceId) {
        return new Line(null, phoneNumber, tariffType, activationDate,
                simCard, status, clientId, employeeId, deviceId);
    }

    public static Line fromPrimitives(Long id, String phoneNumber, String tariffType, LocalDate activationDate,
                                      SIMCard simCard, LineStatus status,
                                      Long clientId, Long employeeId, Long deviceId) {
        return new Line(id, phoneNumber, tariffType, activationDate,
                simCard, status, clientId, employeeId, deviceId);
    }

    // --- Business logic ---
    public boolean isActive() {
        return this.status == LineStatus.ACTIVE;
    }

    public Line changeStatus(LineStatus newStatus) {
        return new Line(this.id, this.phoneNumber.toPrimitive(), this.tariffType.toPrimitive(),
                this.activationDate, this.simCard, newStatus,
                this.clientId, this.employeeId, this.deviceId);
    }

    public Line assignTo(Long employeeId, Long deviceId) {
        return new Line(this.id, this.phoneNumber.toPrimitive(), this.tariffType.toPrimitive(),
                this.activationDate, this.simCard, LineStatus.ACTIVE,
                this.clientId, employeeId, deviceId);
    }

    public Line unassign() {
        return new Line(this.id, this.phoneNumber.toPrimitive(), this.tariffType.toPrimitive(),
                this.activationDate, this.simCard, LineStatus.SUSPENDED,
                this.clientId, null, null);
    }

    public Line updateData(String phoneNumber, String tariffType, SIMCard simCard, LineStatus status,
                           Long clientId, Long employeeId, Long deviceId) {
        return new Line(
                this.id,
                phoneNumber,
                tariffType,
                this.activationDate,
                simCard,
                status,
                clientId,
                employeeId,
                deviceId
        );
    }

}





