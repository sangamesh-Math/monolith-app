package com.example.monolith.service.imple;

import com.example.monolith.model.User;
import com.example.monolith.repository.UserRepository;
import com.example.monolith.service.ServiceInterfaces.UserServiceIntf;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserServiceIntf {
    private final UserRepository userRepository;

    UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if(user != null) {
            userRepository.save(user);
        }
        return user;
    }

}
