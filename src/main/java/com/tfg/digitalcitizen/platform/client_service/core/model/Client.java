package com.tfg.digitalcitizen.platform.client_service.core.model;

import java.time.LocalDate;

public final class Client {

    private Long id;
    private final CompanyName companyName;
    private final Cif cif;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Address address;
    private final LocalDate registrationDate;
    private final ClientStatus status;

    // Getters con conversiones a primitivos
    public Long id() { return id; }
    public String companyName() { return companyName.toPrimitive(); }
    public String cif() { return cif.toPrimitive(); }
    public String email() { return email.toPrimitive(); }
    public String phoneNumber() { return phoneNumber.toPrimitive(); }
    public String address() { return address.toPrimitive(); }
    public LocalDate fechaAlta() { return registrationDate; }
    public String estado() { return status.name(); }

    // Constructor privado
    private Client(Long id, String companyName, String cif, String email, String phoneNumber,
                   String address, LocalDate registrationDate, ClientStatus status) {

        this.id = id;
        this.companyName = CompanyName.fromPrimitive(companyName);
        this.cif = Cif.fromPrimitive(cif);
        this.email = Email.fromPrimitive(email);
        this.phoneNumber = PhoneNumber.fromPrimitive(phoneNumber);
        this.address = Address.fromPrimitive(address);
        this.registrationDate = registrationDate;
        this.status = status;
    }

    // Factory methods
    public static Client fromPrimitives(String companyName, String cif, String email,
                                        String phoneNumber, String address,
                                        LocalDate registrationDate, ClientStatus status) {
        return new Client(null, companyName, cif, email, phoneNumber, address, registrationDate, status);
    }

    public static Client fromPrimitives(Long id, String companyName, String cif, String email,
                                        String phoneNumber, String address,
                                        LocalDate registrationDate, ClientStatus status) {
        return new Client(id, companyName, cif, email, phoneNumber, address, registrationDate, status);
    }

    // Métodos de negocio
    public boolean isActive() {
        return this.status == ClientStatus.ACTIVE;
    }

    public Client deactivate() {
        return new Client(this.id, this.companyName.toPrimitive(), this.cif.toPrimitive(),
                this.email.toPrimitive(), this.phoneNumber.toPrimitive(),
                this.address.toPrimitive(), this.registrationDate, ClientStatus.INACTIVE);
    }
    public Client updateData(String companyName, String cif, String email,
                             String phoneNumber, String address, ClientStatus status) {
        return new Client(
                this.id, // mantiene el ID original
                companyName,
                cif,
                email,
                phoneNumber,
                address,
                this.registrationDate, // mantiene la fecha de registro original
                status
        );
    }
}


