package org.ryanchoi.user_service.service;

import org.ryanchoi.user_service.dto.UserDto;
import org.ryanchoi.user_service.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService  {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
    UserDto getUserDetailsByEmail(String userName);
}
