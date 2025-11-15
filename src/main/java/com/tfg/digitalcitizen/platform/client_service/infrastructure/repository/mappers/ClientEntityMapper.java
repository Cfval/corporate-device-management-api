package com.tfg.digitalcitizen.platform.client_service.infrastructure.repository.mappers;

import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import com.tfg.digitalcitizen.platform.client_service.infrastructure.repository.entity.ClientEntity;

public final class ClientEntityMapper {

    private ClientEntityMapper() {}

    public static Client toDomain(ClientEntity entity) {
        if (entity == null) return null;

        return Client.fromPrimitives(
                entity.getId(),
                entity.getCompanyName(),
                entity.getCif(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getRegistrationDate(),
                entity.getStatus()
        );
    }

    public static ClientEntity toEntity(Client domain) {
        if (domain == null) return null;

        return new ClientEntity(
                domain.id(),
                domain.companyName(),
                domain.cif(),
                domain.email(),
                domain.phoneNumber(),
                domain.address(),
                domain.registrationDate(),
                domain.isActive() ? ClientStatus.ACTIVE : ClientStatus.INACTIVE
        );
    }
}


