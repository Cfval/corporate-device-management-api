package com.tfg.digitalcitizen.platform.client_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.client_service.application.GETClientByIdUseCase;
import com.tfg.digitalcitizen.platform.client_service.application.dto.ClientDto;
import com.tfg.digitalcitizen.platform.client_service.application.mapper.ClientMapper;
import com.tfg.digitalcitizen.platform.client_service.application.model.ClientByIdUseCaseResponse;
import com.tfg.digitalcitizen.platform.client_service.infrastructure.controller.dto.ClientByIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GETClientByIdRestController {

    private final GETClientByIdUseCase useCase;

    @Operation(summary = "Get client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientByIdResponse> findById(@PathVariable Long id) {

        ClientByIdUseCaseResponse response = useCase.invoke(id);
        ClientDto ClientDto = ClientMapper.toDto(response.getClient());
        return ResponseEntity.ok(new ClientByIdResponse(ClientDto));
    }
}
