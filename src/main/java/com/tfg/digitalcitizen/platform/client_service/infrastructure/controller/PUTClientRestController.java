package com.tfg.digitalcitizen.platform.client_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.client_service.application.PUTClientUseCase;
import com.tfg.digitalcitizen.platform.client_service.application.dto.ClientDto;
import com.tfg.digitalcitizen.platform.shared.api.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PUTClientRestController {

    private final PUTClientUseCase useCase;

    @Operation(summary = "Update client information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/clients/{id}")
    public ResponseEntity<ApiSuccessResponse<ClientDto>> update(@PathVariable Long id, @RequestBody ClientDto dto,
                                                                HttpServletRequest request) {

        ClientDto updated = useCase.invoke(id, dto);

        return ResponseEntity.ok(ApiSuccessResponse.of(updated, 200, request.getRequestURI()));
    }
}
