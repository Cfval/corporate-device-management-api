package com.tfg.digitalcitizen.platform.user_service.application;

import com.tfg.digitalcitizen.platform.user_service.application.dto.UserDto;
import com.tfg.digitalcitizen.platform.user_service.application.mapper.UserMapper;
import com.tfg.digitalcitizen.platform.user_service.core.model.User;
import com.tfg.digitalcitizen.platform.user_service.core.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class POSTUserUseCase {

    private final UserRepositoryPort repository;

    public UserDto invoke(UserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Line data cannot be null");
        }

        User userToSave = UserMapper.toDomain(dto);
        User saved = repository.save(userToSave);
        return UserMapper.toDto(saved);
    }
}
