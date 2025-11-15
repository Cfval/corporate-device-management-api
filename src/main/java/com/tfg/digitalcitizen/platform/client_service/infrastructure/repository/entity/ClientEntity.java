package com.tfg.digitalcitizen.platform.client_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "clients",
        indexes = {
                @Index(name = "idx_client_cif", columnList = "cif"),
                @Index(name = "idx_client_email", columnList = "email"),
                @Index(name = "idx_client_status", columnList = "status")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String companyName;

    @Column(nullable = false, unique = true, length = 16)
    private String cif;

    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 300)
    private String address;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClientStatus status;
}

