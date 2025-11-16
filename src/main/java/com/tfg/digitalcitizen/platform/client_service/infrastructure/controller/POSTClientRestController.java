package com.tfg.digitalcitizen.platform.client_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.client_service.application.POSTClientUseCase;
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
public class POSTClientRestController {

    private final POSTClientUseCase useCase;

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid client data")
    })
    @PostMapping("/clients")
    public ResponseEntity<ApiSuccessResponse<ClientDto>> create(@RequestBody ClientDto dto, HttpServletRequest request) {

        ClientDto saved = useCase.invoke(dto);

        return ResponseEntity.status(201).body(ApiSuccessResponse.of(saved, 201, request.getRequestURI()));
    }
}
