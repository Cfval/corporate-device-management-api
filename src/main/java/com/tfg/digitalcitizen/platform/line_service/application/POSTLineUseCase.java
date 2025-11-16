package com.tfg.digitalcitizen.platform.line_service.application;

import com.tfg.digitalcitizen.platform.line_service.application.dto.LineDto;
import com.tfg.digitalcitizen.platform.line_service.application.mapper.LineMapper;
import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class POSTLineUseCase {

    private final LineRepositoryPort repository;

    public LineDto invoke(LineDto lineDto) {
        if (lineDto == null) {
            throw new IllegalArgumentException("Line data cannot be null");
        }

        Line lineToSave = LineMapper.toDomain(lineDto);
        Line saved = repository.save(lineToSave);
        return LineMapper.toDto(saved);
    }
}
