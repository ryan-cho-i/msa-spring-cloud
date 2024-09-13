package org.ryanchoi.user_service.service;

import org.ryanchoi.user_service.dto.UserDto;
import org.ryanchoi.user_service.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
