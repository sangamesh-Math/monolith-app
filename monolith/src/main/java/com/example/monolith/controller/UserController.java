package com.example.monolith.controller;

import com.example.monolith.dto.UserCreateDto;
import com.example.monolith.dto.UserResponseDto;
import com.example.monolith.dto.UserUpdateDto;
import com.example.monolith.service.ServiceInterfaces.UserServiceIntf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.monolith.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceIntf userService;

    public UserController(UserServiceIntf userService) {
        this.userService = userService;
    }

    @PostMapping
    UserResponseDto createUser(@RequestBody UserCreateDto user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    UserResponseDto getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    UserResponseDto updateUserById(@PathVariable Long id, @RequestBody UserUpdateDto user) {
        return userService.updateUser(id, user);
    }
}
