package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "devices",
        indexes = {
                @Index(name = "idx_device_imei", columnList = "imei"),
                @Index(name = "idx_device_status", columnList = "status"),
                @Index(name = "idx_device_client", columnList = "clientId"),
                @Index(name = "idx_device_employee", columnList = "employeeId"),
                @Index(name = "idx_device_type", columnList = "type")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Campos del dominio ---

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DeviceType type;

    @Column(nullable = false, unique = true, length = 30)
    private String imei;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = true, length = 100)
    private String serialNumber;

    @Column(nullable = true, length = 50)
    private String os;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DeviceStatus status;

    @Column(nullable = false)
    private LocalDate activationDate;

    // --- Relaciones lógicas (NO FK entre microservicios) ---

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = true)
    private Long lineId;

    @Column(nullable = true)
    private Long employeeId;
}


