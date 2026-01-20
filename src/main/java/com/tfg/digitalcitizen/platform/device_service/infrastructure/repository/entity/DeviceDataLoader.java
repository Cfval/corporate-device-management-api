package com.tfg.digitalcitizen.platform.device_service.infrastructure.repository.entity;

import com.tfg.digitalcitizen.platform.device_service.core.model.*;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@Order(4)
public class DeviceDataLoader implements CommandLineRunner {

    private final DeviceRepositoryPort repository;
    private final UserRepositoryPort userRepository;
    private static final Random random = new Random();

    public DeviceDataLoader(DeviceRepositoryPort repository, UserRepositoryPort userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    private static final String[] BRANDS = {
            "Samsung", "Apple", "Xiaomi", "Lenovo", "HP", "Dell", "Huawei", "Google"
    };

    private static final String[] ANDROID_MODELS = {
            "Galaxy S21", "Galaxy A52", "Mi 11", "Pixel 6", "P50 Pro"
    };

    private static final String[] IOS_MODELS = {
            "iPhone 12", "iPhone 13", "iPhone 14"
    };

    private static final String[] LAPTOP_MODELS = {
            "ThinkPad X1", "MacBook Pro", "MateBook X", "Dell XPS 13"
    };

    private static final String[] OS_TYPES = {
            "Android", "iOS", "Windows", "MacOS", "Linux", "Otro"
    };

    @Override
    public void run(String... args) throws Exception {

        if (!repository.findAll().isEmpty()) {
            System.out.println("Base de datos ya contiene dispositivos. Precarga omitida.");
            return;
        }

        int totalDevices = 200;
        System.out.println("Generando " + totalDevices + " dispositivos aleatorios...");

        for (int i = 1; i <= totalDevices; i++) {

            DeviceType type = randomDeviceType();

            String imei = generateImei(i);
            String brand = randomBrand();
            String model = randomModel(type);
            String os = randomOs(type);

            LocalDate activationDate = LocalDate.now().minusDays(random.nextInt(1500));
            DeviceStatus status = randomDeviceStatus();

            Long clientId = (long) (1 + random.nextInt(15));

            Long employeeId = assignEmployeeForClient(clientId, status);
            Long lineId = assignLineForEmployee(employeeId, status);

            repository.save(Device.fromPrimitives(
                    type,
                    imei,
                    brand,
                    model,
                    randomSerial(),
                    os,
                    status,
                    activationDate,
                    clientId,
                    lineId,
                    employeeId
            ));
        }

        System.out.println("Dispositivos generados correctamente.");
    }

    // ==========================================================
    // HELPERS
    // ==========================================================

    private DeviceType randomDeviceType() {
        DeviceType[] types = DeviceType.values();
        return types[random.nextInt(types.length)];
    }

    private String randomBrand() {
        return BRANDS[random.nextInt(BRANDS.length)];
    }

    private String randomModel(DeviceType type) {
        return switch (type) {
            case SMARTPHONE -> ANDROID_MODELS[random.nextInt(ANDROID_MODELS.length)];
            case TABLET -> ANDROID_MODELS[random.nextInt(ANDROID_MODELS.length)];
            case LAPTOP -> LAPTOP_MODELS[random.nextInt(LAPTOP_MODELS.length)];
            case DESKTOP -> LAPTOP_MODELS[random.nextInt(LAPTOP_MODELS.length)];
            case SMARTWATCH -> "Watch Series " + (1 + random.nextInt(7));
            default -> "Generic Model";
        };
    }

    private String randomOs(DeviceType type) {
        return switch (type) {
            case SMARTPHONE, TABLET -> "Android";
            case SMARTWATCH -> "WearOS";
            case LAPTOP, DESKTOP -> "Windows";
            default -> OS_TYPES[random.nextInt(OS_TYPES.length)];
        };
    }

    private DeviceStatus randomDeviceStatus() {
        int p = random.nextInt(100);

        if (p < 50) return DeviceStatus.ASSIGNED;
        if (p < 75) return DeviceStatus.STORAGE;
        if (p < 85) return DeviceStatus.REPAIR;
        if (p < 90) return DeviceStatus.LOST;
        return DeviceStatus.DECOMMISSIONED;
    }

    private String generateImei(int i) {
        return "35" + String.format("%013d", i);
    }

    private String randomSerial() {
        return "SN" + (1000 + random.nextInt(9000));
    }

    /**
     * Asigna employeeId SOLO si:
     *  - El dispositivo está ASSIGNED
     *  - EXISTE un usuario activo del mismo cliente
     */
    private Long assignEmployeeForClient(Long clientId, DeviceStatus status) {

        if (status != DeviceStatus.ASSIGNED) return null;
        if (random.nextInt(100) >= 70) return null; // 70% probabilidad

        List<User> users = userRepository.findByClientId(clientId)
                .stream()
                .filter(User::isActive)
                .toList();

        if (users.isEmpty()) return null;

        User u = users.get(random.nextInt(users.size()));
        return u.id();
    }

    /**
     * Asigna lineId SOLO si:
     *  - El dispositivo está asignado
     *  - El empleado tiene una línea asignada
     */
    private Long assignLineForEmployee(Long employeeId, DeviceStatus status) {

        if (status != DeviceStatus.ASSIGNED) return null;
        if (employeeId == null) return null;

        return userRepository.findById(employeeId)
                .map(User::lineId)
                .orElse(null);
    }
}



