package com.example.monolith.service.ServiceInterfaces;

import com.example.monolith.model.User;

import java.util.List;

public interface UserServiceIntf {
    User createUser(User user);
    User getUser(Long id);
    void deleteUser(Long id);
    User updateUser(Long id, User user);
    List<User> getAllUsers();
}
