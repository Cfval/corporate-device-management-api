package com.tfg.digitalcitizen.platform.user_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.model.UserStatus;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.Random;

@Component
@Order(2)
public class UserDataLoader implements CommandLineRunner {

    private final UserRepositoryPort repository;
    private static final Random random = new Random();

    private static final String[] FIRST_NAMES = {
            "Carlos", "Laura", "Marta", "Sergio", "Ana", "David", "Lucía", "Pablo", "Elena", "Jorge",
            "Andrea", "Marcos", "Irene", "Raúl", "Cristina", "Víctor", "Daniel", "Paula", "Alba", "Rubén"
    };

    private static final String[] LAST_NAMES = {
            "García", "Pérez", "Martínez", "López", "Sánchez", "Ramírez", "Gómez", "Díaz", "Navarro",
            "Torres", "Vargas", "Castillo", "Molina", "Suárez", "Ortega", "Rubio", "Romero", "Cortés"
    };

    private static final String[] DEPARTMENTS = {
            "IT", "RRHH", "Marketing", "Contabilidad", "Operaciones", "Soporte"
    };

    // Constructor Injection
    public UserDataLoader(UserRepositoryPort repository) {
        this.repository = repository;
    }

    // EJECUCIÓN DEL LOADER
    @Override
    public void run(String... args) throws Exception {

        if (!repository.findAll().isEmpty()) {
            System.out.println("Base de datos ya contiene usuarios. Precarga omitida.");
            return;
        }

        int totalUsers = 150;
        System.out.println("Generando " + totalUsers + " usuarios aleatorios...");

        for (int i = 1; i <= totalUsers; i++) {

            String fullName = generateFullName();
            Long clientId = (long) (1 + random.nextInt(15)); // 1..15
            String email = generateEmail(fullName, clientId, i);

            String department = randomDepartment();
            LocalDate registration = LocalDate.now().minusDays(random.nextInt(1500));
            UserStatus status = randomUserStatus();
            String role = randomRole();

            // NO asignamos línea aquí, lo hará LineUserSyncLoader
            Long lineId = null;

            repository.save(User.fromPrimitives(
                    fullName,
                    email,
                    department,
                    registration,
                    status,
                    role,
                    clientId,
                    lineId
            ));
        }

        System.out.println("Usuarios generados correctamente.");
    }


    // HELPERS
    private String generateFullName() {
        String first = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String last = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return first + " " + last;
    }

    private String generateEmail(String fullName, Long clientId, int unique) {
        String normalized = normalize(fullName)
                .toLowerCase()
                .replace(" ", ".")
                .replaceAll("[^a-z0-9._-]", "");

        return normalized + unique + "@cliente" + clientId + ".com";
    }

    private String normalize(String text) {
        String norm = java.text.Normalizer.normalize(text, java.text.Normalizer.Form.NFD);
        return norm.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private String randomDepartment() {
        return random.nextInt(100) < 80
                ? DEPARTMENTS[random.nextInt(DEPARTMENTS.length)]
                : null;
    }

    private UserStatus randomUserStatus() {
        return random.nextInt(100) < 85 ? UserStatus.ACTIVE : UserStatus.INACTIVE;
    }

    private String randomRole() {
        int p = random.nextInt(100);

        if (p < 50) return "EMPLEADO";
        if (p < 60) return "GERENTE";
        if (p < 75) return "TÉCNICO";
        if (p < 90) return "ANALISTA";
        if (p < 95) return "SOPORTE";
        return "ADMIN";
    }
}



