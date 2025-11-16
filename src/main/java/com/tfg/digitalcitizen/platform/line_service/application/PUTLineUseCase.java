package com.tfg.digitalcitizen.platform.line_service.application;

import com.tfg.digitalcitizen.platform.line_service.application.dto.LineDto;
import com.tfg.digitalcitizen.platform.line_service.application.mapper.LineMapper;
import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.model.LineStatus;
import com.tfg.digitalcitizen.platform.line_service.core.model.simcard.SIMCard;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import com.tfg.digitalcitizen.platform.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PUTLineUseCase {

    private final LineRepositoryPort repository;

    public LineDto invoke(Long id, LineDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Line data cannot be null");
        }

        Line existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Line not found with ID: " + id));

        SIMCard sim = SIMCard.fromPrimitives(
                dto.getIccid(),
                dto.getSimType(),
                dto.getPin(),
                dto.getPuk(),
                dto.getOperator()
        );

        LineStatus Status = LineStatus.valueOf(dto.getStatus().toUpperCase());

        Line updated = existing.updateData(
                dto.getPhoneNumber(),
                dto.getTariffType(),
                sim,
                Status,
                dto.getClientId(),
                dto.getEmployeeId(),
                dto.getDeviceId()
        );

        Line saved = repository.update(updated);
        return LineMapper.toDto(saved);
    }
}
