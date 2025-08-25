package com.example.monolith.service.imple;

import com.example.monolith.dto.UserCreateDto;
import com.example.monolith.dto.UserMapperDto;
import com.example.monolith.dto.UserResponseDto;
import com.example.monolith.dto.UserUpdateDto;
import com.example.monolith.exception.ResourceNotFoundException;
import com.example.monolith.model.User;
import com.example.monolith.repository.UserRepository;
import com.example.monolith.service.ServiceInterfaces.UserServiceIntf;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserServiceIntf {
    private final UserRepository userRepository;

    public UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserCreateDto dto) {
        User user = UserMapperDto.toEntity(dto);
        return UserMapperDto.toResponseDto(userRepository.save(user));
    }

    public UserResponseDto getUser(Long id) {
        return UserMapperDto.toResponseDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id + "The above is a defined custom exception ")));
    }

    public void deleteUser(Long id) {
       User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid id: " + id));
       userRepository.delete(user);
    }

    public UserResponseDto updateUser(Long id, UserUpdateDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        UserMapperDto.updateEntity(existing, dto);
        return UserMapperDto.toResponseDto(userRepository.save(existing));

    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapperDto::toResponseDto)
                .collect(Collectors.toList());
    }
}
