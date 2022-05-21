package com.toyvalley.services;

import com.toyvalley.data.ToyTest;
import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.repositories.ToyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ToyServiceUnitTest {

    @MockBean
    private ToyRepository toyRepository;

    @TestConfiguration
    static class ToyServiceTestContextConfiguration {
        @Bean
        @Primary
        public ToyService toyService(ToyRepository toyRepository) {
            return new ToyService(toyRepository);
        }
    }

    @Autowired
    private ToyService toyService;

    @Test
    public void givenToys_whenGetToys_thenListShouldBeFound() {
        ArrayList<Toy> toyResponse = new ArrayList<>();
        toyResponse.add(ToyTest.toy());
        Mockito.when(toyRepository.findAll()).thenReturn(toyResponse);

        List<ToyResponse> returnedItems = toyService.getToy();

        assertThat(returnedItems).hasSize(1);
    }

    @Test
    public void givenNoToys_whenGetToys_thenListShouldBeEmpty() {
        assertThat(toyService.getToy()).isEmpty();
    }
}
