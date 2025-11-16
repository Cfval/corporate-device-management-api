package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.DELETEDeviceUseCase;
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
public class DELETEDeviceRestController {

    private final DELETEDeviceUseCase useCase;

    @Operation(summary = "Delete a device by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Device deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Device not found")
    })
    @DeleteMapping("/devices/{id}")
    public ResponseEntity<ApiDeleteResponse> delete(@PathVariable Long id, HttpServletRequest request) {

        Long deletedId = useCase.invoke(id);

        return ResponseEntity.ok(
                ApiDeleteResponse.of(
                        deletedId,
                        "Device deleted successfully.",
                        200,
                        request.getRequestURI()
                )
        );
    }
}
