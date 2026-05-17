package com.example.unit_testing_principles_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    
    private String email;
    private String password;
    private String passwordConfirm;
    private String username;
}
