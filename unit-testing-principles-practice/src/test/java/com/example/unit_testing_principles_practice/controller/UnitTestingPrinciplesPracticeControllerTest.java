package com.example.unit_testing_principles_practice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UnitTestingPrinciplesPracticeController.class)
class UnitTestingPrinciplesPracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGreeting_returnsHelloName() throws Exception {
        mockMvc.perform(get("/greet").param("name", "Ryo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Ryo!"));
    }
}
