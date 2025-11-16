package com.tfg.digitalcitizen.platform.client_service.application;

import com.tfg.digitalcitizen.platform.client_service.application.dto.ClientDto;
import com.tfg.digitalcitizen.platform.client_service.application.mapper.ClientMapper;
import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PUTClientUseCase {

    private final ClientRepositoryPort repository;

    public ClientDto invoke(Long id, ClientDto clientDto) {
        if (clientDto == null) {
            throw new IllegalArgumentException("Client data cannot be null");
        }

        // Buscar el cliente existente
        Client existingClient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with ID: " + id));

        ClientStatus newStatus;
        try {
            newStatus = ClientStatus.valueOf(clientDto.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid client status: " + clientDto.getStatus());
        }

        Client updatedClient = existingClient.updateData(
                clientDto.getCompanyName(),
                clientDto.getCif(),
                clientDto.getEmail(),
                clientDto.getPhoneNumber(),
                clientDto.getAddress(),
                newStatus
        );

        Client savedClient = repository.update(updatedClient);
        return ClientMapper.toDto(savedClient);
    }
}


