package com.tfg.digitalcitizen.platform.user_service.application;

import com.tfg.digitalcitizen.platform.user_service.application.model.UsersUseCaseResponse;
import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GETUsersByClientIdUseCase {

    private final UserRepositoryPort repository;

    public UsersUseCaseResponse invoke(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        List<User> users = repository.findByClientId(clientId);
        Integer totalUsers = users.size();
        Integer totalUsersFiltered = users.size();

        return new UsersUseCaseResponse(users, totalUsers, totalUsersFiltered);
    }
}
