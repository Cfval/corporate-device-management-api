package com.tfg.digitalcitizen.platform.client_service.application;

import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DELETEClientUseCase {

    private final ClientRepositoryPort repository;

    public Long invoke(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }

        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with ID " + id + " not found"));

        repository.deleteById(id);
        return id;
    }
}



