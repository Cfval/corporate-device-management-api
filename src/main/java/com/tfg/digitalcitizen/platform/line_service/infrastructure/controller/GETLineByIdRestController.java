package com.tfg.digitalcitizen.platform.line_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.line_service.application.GETLineByIdUseCase;
import com.tfg.digitalcitizen.platform.line_service.application.dto.LineDto;
import com.tfg.digitalcitizen.platform.line_service.application.mapper.LineMapper;
import com.tfg.digitalcitizen.platform.line_service.application.model.LineByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.line_service.infrastructure.controller.dto.LineByIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class GETLineByIdRestController {

    private final GETLineByIdUseCase useCase;

    @Operation(summary = "Get line by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Line found successfully"),
            @ApiResponse(responseCode = "404", description = "Line not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/lines/{id}")
    public ResponseEntity<LineByIdResponse> findById(@PathVariable Long id) {

        LineByIdUseCaseResponse response = useCase.invoke(id);
        LineDto dto = LineMapper.toDto(response.getLine());
        return ResponseEntity.ok(new LineByIdResponse(dto));
    }
}

