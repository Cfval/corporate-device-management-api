package com.tfg.digitalcitizen.platform.user_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.shared.api.ApiSuccessResponse;
import com.tfg.digitalcitizen.platform.user_service.application.PUTUserUseCase;
import com.tfg.digitalcitizen.platform.user_service.application.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PUTUserRestController {

    private final PUTUserUseCase useCase;

    @Operation(summary = "Update user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<ApiSuccessResponse<UserDto>> update(@PathVariable Long id, @RequestBody UserDto dto,
                                                              HttpServletRequest request) {
        UserDto updated = useCase.invoke(id, dto);

        return ResponseEntity.ok(ApiSuccessResponse.of(updated, 200, request.getRequestURI()));
    }

}

