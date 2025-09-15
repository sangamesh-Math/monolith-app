package com.example.monolith.service.ServiceInterfaces;

import com.example.monolith.dto.UserCreateDto;
import com.example.monolith.dto.UserResponseDto;
import com.example.monolith.dto.UserUpdateDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

public interface UserServiceIntf {

    @CacheEvict(value = "usersAll", allEntries = true)
    UserResponseDto createUser(UserCreateDto dto);

    @Cacheable(value = "users", key = "#id")
    UserResponseDto getUser(Long id);

    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "usersAll", allEntries = true)
    })
    void deleteUser(Long id);

    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "usersAll", allEntries = true)
    })
    UserResponseDto updateUser(Long id, UserUpdateDto dto);

    @Cacheable(value = "usersAll")
    List<UserResponseDto> getAllUsers();
}

