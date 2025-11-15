package com.tfg.digitalcitizen.platform.device_service.infrastructure.controller;

import com.tfg.digitalcitizen.platform.device_service.application.GETDevicesByEmployeeUseCase;
import com.tfg.digitalcitizen.platform.device_service.application.dto.DeviceDto;
import com.tfg.digitalcitizen.platform.device_service.application.mapper.DeviceMapper;
import com.tfg.digitalcitizen.platform.device_service.application.model.DevicesUseCaseResponse;
import com.tfg.digitalcitizen.platform.device_service.infrastructure.controller.dto.DevicesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GETDevicesByEmployeeRestController {

    private final GETDevicesByEmployeeUseCase useCase;

    @GetMapping("/devices/employee/{employeeId}")
    public ResponseEntity<DevicesResponse> findByEmployee(@PathVariable Long employeeId) {

        DevicesUseCaseResponse devicesResponse = useCase.invoke(employeeId);

        List<DeviceDto> dtoList = devicesResponse.getDevices().stream()
                .map(DeviceMapper::toDto)
                .toList();

        return ResponseEntity.ok(
                new DevicesResponse(dtoList,
                        devicesResponse.getTotalDevices(),
                        devicesResponse.getTotalDevicesFiltered())
        );
    }
}
