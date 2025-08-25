package com.example.monolith.dto;

import com.example.monolith.model.User;

public class UserMapperDto {
    public static User toEntity(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    public static void updateEntity(User user, UserUpdateDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
    }
}
