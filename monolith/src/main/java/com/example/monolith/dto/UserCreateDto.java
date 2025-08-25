package com.example.monolith.dto;

import jakarta.validation.constraints.*;

public class UserCreateDto {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50)
   private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
   private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
