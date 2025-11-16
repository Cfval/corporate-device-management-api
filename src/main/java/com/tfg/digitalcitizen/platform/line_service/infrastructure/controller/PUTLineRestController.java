package com.tfg.digitalcitizen.platform.line_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.line_service.application.PUTLineUseCase;
import com.tfg.digitalcitizen.platform.line_service.application.dto.LineDto;
import com.tfg.digitalcitizen.platform.shared.api.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PUTLineRestController {

    private final PUTLineUseCase useCase;

    @Operation(summary = "Update line")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Line updated successfully"),
            @ApiResponse(responseCode = "404", description = "Line not found")
    })
    @PutMapping("/lines/{id}")
    public ResponseEntity<ApiSuccessResponse<LineDto>> update(@PathVariable Long id, @RequestBody LineDto dto,
                                                              HttpServletRequest request) {

        LineDto updated = useCase.invoke(id, dto);

        return ResponseEntity.ok(ApiSuccessResponse.of(updated, 200, request.getRequestURI()));
    }
}
