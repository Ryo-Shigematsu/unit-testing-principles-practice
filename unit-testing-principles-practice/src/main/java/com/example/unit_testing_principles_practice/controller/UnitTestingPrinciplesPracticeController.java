package com.example.unit_testing_principles_practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnitTestingPrinciplesPracticeController {
    
    @GetMapping("/greet")
    public String getGreeting(@RequestParam String name) {
        return "Hello, " + name + "!";
    }
}
