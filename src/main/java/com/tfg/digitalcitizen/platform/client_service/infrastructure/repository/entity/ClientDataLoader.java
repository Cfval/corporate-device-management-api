package com.tfg.digitalcitizen.platform.client_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.client_service.core.model.Client;
import com.tfg.digitalcitizen.platform.client_service.core.model.ClientStatus;
import com.tfg.digitalcitizen.platform.client_service.core.ports.ClientRepositoryPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@Order(1) // Se ejecuta primero
public class ClientDataLoader implements CommandLineRunner {

    private final ClientRepositoryPort repository;
    private static final Random random = new Random();

    public ClientDataLoader(ClientRepositoryPort repository) {
        this.repository = repository;
    }

    private static final String[] COMPANY_NAMES = {
            "BlueTech Solutions", "InnovaSoft", "Digital Nexus",
            "PrimeData Systems", "CyberWorks Studios", "SmartSystems Corp",
            "Skyline Technologies", "Vertex Global", "FutureNet Labs",
            "Apex Digital Group", "BrightWave IT", "NeonSoft Innovations",
            "Orion Data Group", "Solstice IT Partners", "Quantum Corp"
    };

    private static final String[] STREETS = {
            "Europa", "Alicante", "Valencia", "Camino de la Virgen", "Onil",
            "Barrios bajos", "Oceano", "La Mancha", "Castilla", "Zapatero"
    };

    @Override
    public void run(String... args) throws Exception {

        if (!repository.findAll().isEmpty()) {
            System.out.println("Base de datos ya contiene clientes. Precarga omitida.");
            return;
        }

        int totalClients = 15;
        System.out.println("Generando " + totalClients + " empresas cliente...");

        for (int i = 1; i <= totalClients; i++) {

            String companyName = COMPANY_NAMES[i - 1] + " S.L.";
            String cif = generateCif(i);
            String email = "contact@client" + i + ".com";
            String phone = generatePhone(i);
            String address = generateAddress();

            LocalDate registrationDate = LocalDate.now().minusDays(random.nextInt(1500));

            ClientStatus status = (random.nextInt(100) < 70)
                    ? ClientStatus.ACTIVE
                    : ClientStatus.INACTIVE;

            repository.save(Client.fromPrimitives(
                    companyName,
                    cif,
                    email,
                    phone,
                    address,
                    registrationDate,
                    status
            ));
        }

        System.out.println("Clientes generados correctamente.");
    }

    // HELPERS
    private String generateCif(int i) {
        return "B" + String.format("%08d", 10_000_000 + i);
    }

    private String generatePhone(int i) {
        return "6" + String.format("%08d", i);
    }

    private String generateAddress() {
        String street = STREETS[random.nextInt(STREETS.length)];
        int number = 10 + random.nextInt(90);
        return "Calle " + street + " nº " + number;
    }
}




