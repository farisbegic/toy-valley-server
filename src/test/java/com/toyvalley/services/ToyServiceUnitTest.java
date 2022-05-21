package com.toyvalley.services;

import com.toyvalley.repositories.ToyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class ToyServiceTest {
    private MockMvc mockMvc;

    @Autowired
    private ToyRepository toyRepository;

    @Test
    public void givenToys_whenGetToys_thenReturnToys() {

    }

}
