package com.tfg.digitalcitizen.platform.user_service.core.model;

import java.time.LocalDate;

public final class User {

    private Long id;
    private final FullName fullName;
    private final Email email;
    private final Department department;
    private final LocalDate registrationDate;
    private final UserStatus status;
    private final UserRole role;
    private final Long clientId;
    private final Long lineId;

    // --- Getters ---
    public Long id() { return id; }
    public String fullName() { return fullName.toPrimitive(); }
    public String email() { return email.toPrimitive(); }
    public String department() { return department.toPrimitive(); }
    public LocalDate registrationDate() { return registrationDate; }
    public String status() { return status.name(); }
    public String role() { return role.toPrimitive(); }
    public Long clientId() { return clientId; }
    public Long lineId() { return lineId; }

    // --- Constructor privado ---
    private User(Long id, String fullName, String email, String department,
                 LocalDate registrationDate, UserStatus status, String role,
                 Long clientId, Long lineId) {

        if (fullName == null || registrationDate == null || status == null || clientId == null)
            throw new IllegalArgumentException("Campos obligatorios no pueden ser nulos.");

        this.id = id;
        this.fullName = FullName.fromPrimitive(fullName);
        this.email = Email.fromPrimitive(email);
        this.department = Department.fromPrimitive(department);
        this.registrationDate = registrationDate;
        this.status = status;
        this.role = UserRole.fromPrimitive(role);
        this.clientId = clientId;
        this.lineId = lineId;
    }

    // --- Factory methods ---
    public static User fromPrimitives(String fullName, String email, String department,
                                      LocalDate registrationDate, UserStatus status, String role,
                                      Long clientId, Long lineId) {
        return new User(null, fullName, email, department, registrationDate, status, role, clientId, lineId);
    }

    public static User fromPrimitives(Long id, String fullName, String email, String department,
                                      LocalDate registrationDate, UserStatus status, String role,
                                      Long clientId, Long lineId) {
        return new User(id, fullName, email, department, registrationDate, status, role,  clientId, lineId);
    }

    // --- Business logic ---
    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    public User deactivate() {
        return new User(this.id, this.fullName.toPrimitive(), this.email.toPrimitive(),
                this.department.toPrimitive(), this.registrationDate,
                UserStatus.INACTIVE, this.role.toPrimitive(), this.clientId, this.lineId);
    }
}

