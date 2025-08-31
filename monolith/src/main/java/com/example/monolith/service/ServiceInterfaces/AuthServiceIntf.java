package com.example.monolith.service.ServiceInterfaces;

import com.example.monolith.dto.RegisterRequestDto;

public interface AuthServiceIntf {
    void register(RegisterRequestDto request);
}
