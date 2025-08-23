package com.example.monolith.service.imple;

import com.example.monolith.model.User;
import com.example.monolith.repository.UserRepository;
import com.example.monolith.service.ServiceInterfaces.UserServiceIntf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements UserServiceIntf {
    private final UserRepository userRepository;

    public UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if(user != null) {
            userRepository.save(user);
        }
        return user;
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public String deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "User deleted successfully";
        }
        else
            return "Invalid User Id";
    }

    public User updateUser(Long id, User user) {
        User existing = getUser(id);
        existing.setEmail(user.getEmail());
        existing.setName(user.getName());
        return userRepository.save(existing);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
