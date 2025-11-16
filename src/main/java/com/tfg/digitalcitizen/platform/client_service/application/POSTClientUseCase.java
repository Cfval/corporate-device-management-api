package com.tfg.digitalcitizen.platform.client_service.application;

import com.tfg.digitalcitizen.platform.client_service.application.dto.ClientDto;
import com.tfg.digitalcitizen.platform.client_service.application.mapper.ClientMapper;
import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class POSTClientUseCase {

    private final ClientRepositoryPort repository;

    public ClientDto invoke(ClientDto clientDto) {
        if (clientDto == null) {
            throw new IllegalArgumentException("Client data cannot be null");
        }

        // El id vendrá null
        Client clientToSave = ClientMapper.toDomain(clientDto);
        Client savedClient = repository.save(clientToSave);
        return ClientMapper.toDto(savedClient);
    }
}



