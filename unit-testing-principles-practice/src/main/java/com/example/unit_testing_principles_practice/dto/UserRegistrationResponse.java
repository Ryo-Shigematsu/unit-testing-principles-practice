package com.example.unit_testing_principles_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    
    private Long id;
    private String email;
    private String username;
    private Boolean enabled;
    private String message;
}
