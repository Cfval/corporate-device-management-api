package com.tfg.digitalcitizen.platform.user_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.shared.api.ApiSuccessResponse;
import com.tfg.digitalcitizen.platform.user_service.application.POSTUserUseCase;
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
public class POSTUserRestController {

    private final POSTUserUseCase useCase;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping("/users")
    public ResponseEntity<ApiSuccessResponse<UserDto>> create(@RequestBody UserDto dto, HttpServletRequest request) {

        UserDto saved = useCase.invoke(dto);

        return ResponseEntity.status(201).body(ApiSuccessResponse.of(saved, 201, request.getRequestURI()));
    }
}
