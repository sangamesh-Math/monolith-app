package com.example.monolith.controller;

import com.example.monolith.service.ServiceInterfaces.UserServiceIntf;
import org.springframework.web.bind.annotation.*;
import com.example.monolith.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceIntf userService;

    UserController(UserServiceIntf userService) {
        this.userService = userService;
    }

    @PostMapping
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
