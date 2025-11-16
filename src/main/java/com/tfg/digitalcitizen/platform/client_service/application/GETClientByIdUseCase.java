package com.tfg.digitalcitizen.platform.client_service.application;

import com.tfg.digitalcitizen.platform.client_service.application.model.ClientByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GETClientByIdUseCase {

    private final ClientRepositoryPort repository;

    public ClientByIdUseCaseResponse invoke(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Client client = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with ID: " + id));

        return new ClientByIdUseCaseResponse(client);
    }
}

