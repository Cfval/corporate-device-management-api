package com.tfg.digitalcitizen.platform.line_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.model.LineStatus;
import com.tfg.digitalcitizen.platform.line_service.core.model.simcard.SIMCard;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@Order(3)
public class LineDataLoader implements CommandLineRunner {

    private final LineRepositoryPort lineRepository;
    private final UserRepositoryPort userRepository;

    private static final Random random = new Random();

    private static final String[] TARIFFS = {

            // Datacentric: planes corporativos por GB
            "Empresa Datos 5GB",
            "Empresa Datos 10GB",
            "Empresa Datos 25GB",
            "Empresa Datos 50GB",
            "Empresa Datos 100GB",

            // Ilimitadas reales o “soft”
            "Datos Ilimitados Pro",
            "Datos Ilimitados Max",
            "Datos Ilimitados Business",

            //  Voz + Datos (mixtas corporativas)
            "Voz Ilimitada + 20GB",
            "Voz Ilimitada + 50GB",
            "Voz Ilimitada + 100GB",

            // Segmento autónomos / pymes
            "Plan Autónomos 15GB",
            "Plan Pyme 30GB",

            // IoT / M2M / SIM secundaria
            "Tarifa IoT 500MB",
            "Tarifa M2M 1GB",

            // Tarjetas de datos puramente
            "Solo Datos 20GB",
            "Solo Datos 40GB",

            // Planes internacionales
            "Roaming Pro Europa",
            "Roaming Global Business"
    };

    private static final String[] OPERATORS = {
            "Movistar", "Orange", "Vodafone", "Pepephone"
    };

    public LineDataLoader(LineRepositoryPort lineRepository,
                          UserRepositoryPort userRepository) {
        this.lineRepository = lineRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!lineRepository.findAll().isEmpty()) {
            System.out.println("La base de datos ya contiene líneas. Precarga omitida.");
            return;
        }

        int totalLines = 250;
        System.out.println("Generando " + totalLines + " líneas aleatorias...");

        for (int i = 1; i <= totalLines; i++) {

            Long clientId = (long) (1 + random.nextInt(15)); // 1..15

            String phone = generatePhoneNumber(i);
            String tariff = randomTariff();
            LocalDate activation = LocalDate.now().minusDays(random.nextInt(1500));
            SIMCard simCard = randomSIMCard(i);
            LineStatus status = randomStatus();

            Long employeeId = assignEmployeeId(clientId, status);
            Long deviceId = assignDeviceId(status, employeeId);

            lineRepository.save(Line.fromPrimitives(
                    phone,
                    tariff,
                    activation,
                    simCard,
                    status,
                    clientId,
                    employeeId,
                    deviceId
            ));
        }

        System.out.println("Líneas generadas correctamente.");
    }

    // ==========================================================
    // HELPERS — lógica realista
    // ==========================================================

    private String generatePhoneNumber(int i) {
        return "6" + String.format("%08d", i);
    }

    private String randomTariff() {
        return TARIFFS[random.nextInt(TARIFFS.length)];
    }

    private LineStatus randomStatus() {
        int p = random.nextInt(100);
        if (p < 55) return LineStatus.ACTIVE;
        if (p < 80) return LineStatus.SUSPENDED;
        return LineStatus.DEACTIVATED;
    }

    private SIMCard randomSIMCard(int i) {
        return SIMCard.fromPrimitives(
                randomICCID(i),
                randomSIMType(),
                randomPIN(),
                randomPUK(),
                randomOperator()
        );
    }

    private String randomICCID(int i) {
        return "893450" + String.format("%013d", i);
    }

    private String randomSIMType() {
        int p = random.nextInt(100);
        if (p < 50) return "SIM";
        if (p < 80) return "ESIM";
        if (p < 90) return "DUAL SIM";
        return "MULTISIM";
    }

    private String randomPIN() {
        return String.format("%04d", random.nextInt(10000));
    }

    private String randomPUK() {
        return String.format("%08d", random.nextInt(100_000_000));
    }

    private String randomOperator() {
        return OPERATORS[random.nextInt(OPERATORS.length)];
    }

    // ==========================================================
    // ASIGNACIONES — reglas coherentes Cliente ⇆ Usuario ⇆ Línea
    // ==========================================================

    private Long assignEmployeeId(Long clientId, LineStatus status) {

        if (status != LineStatus.ACTIVE) return null;

        // 75% probabilidad
        if (random.nextInt(100) >= 75) return null;

        List<User> users = userRepository.findByClientId(clientId)
                .stream()
                .filter(User::isActive)
                .toList();

        if (users.isEmpty()) return null;

        User randomUser = users.get(random.nextInt(users.size()));
        return randomUser.id();
    }

    private Long assignDeviceId(LineStatus status, Long employeeId) {
        if (status != LineStatus.ACTIVE) return null;
        if (employeeId == null) return null;

        if (random.nextInt(100) < 70) {
            return (long) (1 + random.nextInt(200)); // 1..200 device IDs
        }
        return null;
    }
}


