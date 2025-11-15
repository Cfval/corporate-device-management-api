package com.tfg.digitalcitizen.platform.client_service.application.mapper;

import com.tfg.digitalcitizen.platform.client_service.application.dto.ClientDto;
import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import java.util.List;
import java.util.stream.Collectors;

public final class ClientMapper {

    private ClientMapper() {}

    public static ClientDto toDto(Client client) {
        return new ClientDto(
                client.id(),
                client.companyName(),
                client.cif(),
                client.email(),
                client.phoneNumber(),
                client.address(),
                client.registrationDate(),
                client.status()
        );
    }

    public static List<ClientDto> toDtoList(List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Client toDomain(ClientDto dto) {
        return Client.fromPrimitives(
                dto.getId(),
                dto.getCompanyName(),
                dto.getCif(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getAddress(),
                dto.getRegistrationDate(),
                ClientStatus.valueOf(dto.getStatus())
        );
    }
}

