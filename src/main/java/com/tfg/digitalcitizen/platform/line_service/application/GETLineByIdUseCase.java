package com.tfg.digitalcitizen.platform.line_service.application;

import com.tfg.digitalcitizen.platform.line_service.application.model.LineByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GETLineByIdUseCase {

    private final LineRepositoryPort repository;

    public LineByIdUseCaseResponse invoke(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Line line = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Line not found with ID: " + id));

        return new LineByIdUseCaseResponse(line);
    }
}
