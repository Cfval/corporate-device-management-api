package com.tfg.digitalcitizen.platform.line_service.application;

import com.tfg.digitalcitizen.platform.line_service.application.model.LinesUseCaseResponse;
import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GETActiveLinesUseCase {

    private final LineRepositoryPort repository;

    public LinesUseCaseResponse invoke() {

        List<Line> lines = repository.findActiveLines();
        Integer totalLines = lines.size();
        Integer totalLinesFiltered = lines.size();

        return new LinesUseCaseResponse(lines, totalLines, totalLinesFiltered);
    }
}
