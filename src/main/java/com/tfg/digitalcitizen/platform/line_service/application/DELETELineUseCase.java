package com.tfg.digitalcitizen.platform.line_service.application;

import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DELETELineUseCase {

    private final LineRepositoryPort repository;

    public Long invoke(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Line ID cannot be null");
        }

        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Line with ID " + id + " not found"));

        repository.deleteById(id);
        return id;
    }
}

