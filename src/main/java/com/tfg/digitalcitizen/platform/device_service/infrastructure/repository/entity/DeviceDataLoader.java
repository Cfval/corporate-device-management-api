package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceStatus;
import com.tfg.digitalcitizen.platform.device_service.core.model.DeviceType;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DeviceDataLoader {

    @Bean
    CommandLineRunner initDevicesDatabase(DeviceRepositoryPort repository) {
        return args -> {

            if (repository.findAll().isEmpty()) {

                repository.save(Device.fromPrimitives(
                        DeviceType.PHONE,
                        "123456789111111",
                        "Samsung",
                        "Galaxy S21",
                        "SN-0001",
                        "Android",
                        DeviceStatus.ASSIGNED,
                        LocalDate.now(),
                        1L, // clientId
                        null,
                        1L  // employeeId
                ));

                repository.save(Device.fromPrimitives(
                        DeviceType.PHONE,
                        "987654321111111",
                        "Apple",
                        "iPhone 14",
                        "SN-0002",
                        "iOS",
                        DeviceStatus.STORAGE,
                        LocalDate.now(),
                        1L,
                        null,
                        2L
                ));

                System.out.println("Datos iniciales de dispositivos cargados correctamente.");
            } else {
                System.out.println("La base de datos ya contiene dispositivos. No se realizó precarga.");
            }
        };
    }
}

