package com.tfg.digitalcitizen.platform.client_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.client_service.application.DELETEClientUseCase;
import com.tfg.digitalcitizen.platform.shared.api.ApiDeleteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DELETEClientRestController {

    private final DELETEClientUseCase useCase;

    @Operation(summary = "Delete a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<ApiDeleteResponse> deleteClient(@PathVariable Long id, HttpServletRequest request) {

        Long deletedId = useCase.invoke(id);

        ApiDeleteResponse response = ApiDeleteResponse.of(
                deletedId,
                "Client deleted successfully.",
                200,
                request.getRequestURI()
        );

        return ResponseEntity.ok(response);
    }
}

