package com.tfg.digitalcitizen.platform.shared.dataloaders;

import com.tfg.digitalcitizen.platform.line_service.core.model.Line;
import com.tfg.digitalcitizen.platform.line_service.core.ports.LineRepositoryPort;
import com.tfg.digitalcitizen.platform.device_service.core.model.Device;
import com.tfg.digitalcitizen.platform.device_service.core.ports.DeviceRepositoryPort;
import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Order(99)
public class LineUserSyncLoader implements CommandLineRunner {

    private final LineRepositoryPort lineRepository;
    private final UserRepositoryPort userRepository;
    private final DeviceRepositoryPort deviceRepository;

    public LineUserSyncLoader(
            LineRepositoryPort lineRepository,
            UserRepositoryPort userRepository,
            DeviceRepositoryPort deviceRepository
    ) {
        this.lineRepository = lineRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println(">>> LineUserSyncLoader INICIADO");

        List<Line> lines = lineRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Device> devices = deviceRepository.findAll();

        if (lines.isEmpty() || users.isEmpty()) {
            System.out.println("No hay líneas o usuarios para sincronizar.");
            return;
        }

        AtomicInteger userUpdates = new AtomicInteger(0);
        AtomicInteger deviceUpdates = new AtomicInteger(0);

        // ================================================
        // 1) LINEA → USUARIO (si la línea tiene employeeId)
        // ================================================
        for (Line line : lines) {

            Long employeeId = line.employeeId();
            if (employeeId == null) continue;

            userRepository.findById(employeeId).ifPresent(user -> {

                if (user.lineId() == null) {
                    User updated = User.fromPrimitives(
                            user.id(),
                            user.fullName(),
                            user.email(),
                            user.department(),
                            user.registrationDate(),
                            user.statusEnum(),
                            user.role(),
                            user.clientId(),
                            line.id()
                    );

                    userRepository.update(updated);
                    userUpdates.incrementAndGet();
                }
            });
        }

        // ================================================
        // 2) USUARIO → LINEA (si user tiene linea pero la línea no está bien enlazada)
        // ================================================
        for (User user : users) {

            if (user.lineId() == null) continue;

            lineRepository.findById(user.lineId()).ifPresent(line -> {

                // Si la línea NO está asignada al user → corregir
                if (line.employeeId() == null || !line.employeeId().equals(user.id())) {

                    Line updatedLine = Line.fromPrimitives(
                            line.id(),
                            line.phoneNumber(),
                            line.tariffType(),
                            line.activationDate(),
                            line.simCard(),
                            line.statusEnum(),
                            line.clientId(),
                            user.id(),      // employeeId reparado
                            line.deviceId()
                    );

                    lineRepository.update(updatedLine);
                }
            });
        }

        // ================================================
        // 3) DISPOSITIVO → USER → LINE
        // ================================================
        for (Device device : devices) {

            Long userId = device.employeeId();
            if (userId == null) continue;

            userRepository.findById(userId).ifPresent(user -> {

                Long correctLineId = user.lineId();

                // Si usuario NO tiene línea → NO tocar el dispositivo
                if (correctLineId == null) return;

                // Si device no tiene o es diferente → actualizar
                if (device.lineId() == null || !device.lineId().equals(correctLineId)) {

                    Device updatedDevice = Device.fromPrimitives(
                            device.id(),
                            device.typeEnum(),
                            device.imei(),
                            device.brand(),
                            device.model(),
                            device.serialNumber(),
                            device.os(),
                            device.statusEnum(),
                            device.activationDate(),
                            device.clientId(),
                            correctLineId,     // linea correcta del usuario
                            userId
                    );

                    deviceRepository.update(updatedDevice);
                    deviceUpdates.incrementAndGet();
                }
            });
        }

        System.out.println("Sync usuario-línea completado. Actualizados: " + userUpdates.get());
        System.out.println("Sync dispositivo-usuario-línea completado. Actualizados: " + deviceUpdates.get());
    }
}


