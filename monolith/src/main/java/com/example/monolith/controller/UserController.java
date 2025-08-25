package com.example.monolith.controller;

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
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
