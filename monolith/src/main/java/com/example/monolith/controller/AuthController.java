package com.example.monolith.controller;

import com.example.monolith.dto.AuthRequestDto;
import com.example.monolith.dto.AuthResponseDto;
import com.example.monolith.dto.RegisterRequestDto;
import com.example.monolith.security.JwtTokenService;
import com.example.monolith.service.ServiceInterfaces.AuthServiceIntf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtTokenService tokenService;
    private final AuthServiceIntf authService;

    public AuthController(AuthenticationManager authManager, JwtTokenService tokenService, AuthServiceIntf authService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto req) {
        authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto req) {
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        UserDetails principal = (UserDetails) auth.getPrincipal();
        String token = tokenService.generateToken(principal.getUsername());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
