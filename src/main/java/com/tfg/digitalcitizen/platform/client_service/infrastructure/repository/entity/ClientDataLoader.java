package com.tfg.digitalcitizen.platform.client_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ClientDataLoader {

    @Bean
    CommandLineRunner initClientsDatabase(ClientRepositoryPort repository) {
        return args -> {

            if (repository.findAll().isEmpty()) {
                repository.save(Client.fromPrimitives(
                        "Digital Citizen S.L.", "B12345678", "info@digitalcitizen.io",
                        "600123456", "Av. Alicante 45, Madrid",
                        LocalDate.now(), ClientStatus.ACTIVE));

                repository.save(Client.fromPrimitives(
                        "BlueTech Corp", "B87654321", "contact@bluetech.com",
                        "699998877", "Calle Futuro 9, Barcelona",
                        LocalDate.now(), ClientStatus.ACTIVE));

                repository.save(Client.fromPrimitives(
                        "InnovaSoft", "B11223344", "hello@innovasoft.es",
                        "611222333", "Av. Europa 12, Valencia",
                        LocalDate.now(), ClientStatus.INACTIVE));

                System.out.println("Datos iniciales de clientes cargados correctamente en la base de datos.");
            } else {
                System.out.println("Base de datos ya contiene clientes. No se realizó la precarga.");
            }
        };
    }
}


