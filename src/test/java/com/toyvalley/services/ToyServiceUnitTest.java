package com.toyvalley.services;

import com.toyvalley.repositories.ToyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ToyServiceUnitTest {

    @MockBean
    private ToyRepository toyRepository;

    @TestConfiguration
    static class ItemServiceTestContextConfiguration {

        @Bean
        @Primary
        public ToyService toyService(ToyRepository toyRepository) {
            return new ToyService(toyRepository);
        }
    }

    @Autowired
    private ToyService toyService;


}
