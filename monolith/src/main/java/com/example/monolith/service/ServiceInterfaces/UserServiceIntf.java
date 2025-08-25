package com.example.monolith.service.ServiceInterfaces;

import com.example.monolith.dto.UserCreateDto;
import com.example.monolith.dto.UserResponseDto;
import com.example.monolith.dto.UserUpdateDto;

import java.util.List;

public interface UserServiceIntf {
    UserResponseDto createUser(UserCreateDto dto);
    UserResponseDto getUser(Long id);
    void deleteUser(Long id);
    UserResponseDto updateUser(Long id, UserUpdateDto user);
    List<UserResponseDto> getAllUsers();
}
