package com.tfg.digitalcitizen.platform.line_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.line_service.application.DELETELineUseCase;
import com.tfg.digitalcitizen.platform.shared.api.ApiDeleteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DELETELineRestController {

    private final DELETELineUseCase useCase;

    @Operation(summary = "Delete a line by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Line deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Line not found")
    })
    @DeleteMapping("/lines/{id}")
    public ResponseEntity<ApiDeleteResponse> delete(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long deletedId = useCase.invoke(id);

        return ResponseEntity.ok(
                ApiDeleteResponse.of(
                        deletedId,
                        "Line deleted successfully.",
                        200,
                        request.getRequestURI()
                )
        );
    }
}
