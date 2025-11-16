package com.tfg.digitalcitizen.platform.user_service.application;

import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DELETEUserUseCase {

    private final UserRepositoryPort repository;

    public Long invoke(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));

        repository.deleteById(id);
        return id;
    }
}

