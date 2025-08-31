package com.example.monolith.service.imple;

import com.example.monolith.dto.RegisterRequestDto;
import com.example.monolith.model.User;
import com.example.monolith.repository.UserRepository;
import com.example.monolith.service.ServiceInterfaces.AuthServiceIntf;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceIntf {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void register(RegisterRequestDto req) {
        if(repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        repo.save(u);
    }
}
