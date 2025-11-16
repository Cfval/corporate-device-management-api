package com.tfg.digitalcitizen.platform.user_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.shared.api.ApiDeleteResponse;
import com.tfg.digitalcitizen.platform.user_service.application.DELETEUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DELETEUserRestController {

    private final DELETEUserUseCase useCase;

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiDeleteResponse> delete(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long deletedId = useCase.invoke(id);

        return ResponseEntity.ok(
                ApiDeleteResponse.of(
                        deletedId,
                        "User deleted successfully.",
                        200,
                        request.getRequestURI()
                )
        );
    }
}
