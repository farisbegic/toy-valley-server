package com.toyvalley.services;

import com.toyvalley.data.ToyTest;
import com.toyvalley.models.data.toy.CreateToyRequest;
import com.toyvalley.models.data.toy.SearchToyResponse;
import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.data.toy.UpdateToyRequest;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.repositories.CityRepository;
import com.toyvalley.repositories.ToyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ToyServiceUnitTest {

    @MockBean
    private ToyRepository toyRepository;

    @MockBean CityRepository cityRepository;


    @TestConfiguration
    static class ToyServiceTestContextConfiguration {
        @Bean
        @Primary
        public ToyService toyService(ToyRepository toyRepository, CityRepository cityRepository) {
            return new ToyService(toyRepository, cityRepository);
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

    @Test
    public void givenValidId_whenGetToy_thenToyShouldBeFound() {
        Toy toy = ToyTest.toy();
        Mockito.when(toyRepository.findById(toy.getId())).thenReturn(Optional.of(toy));

        ToyResponse returnedToy = toyService.getToy(toy.getId());

        assertThat(returnedToy.getName()).isEqualTo(toy.getName());
    }

    @Test
    public void givenInvalidId_whenGetToy_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> toyService.getToy(2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    public void givenToy_whenCreate_thenToyReturned() {
        Toy inputToy = ToyTest.toy();
        Toy outputToy = ToyTest.toy();

        Mockito.when(toyRepository.save(any(Toy.class))).thenReturn(outputToy);

        CreateToyRequest requestToy = ToyTest.createToyRequest(inputToy);
        ToyResponse returnedToy = toyService.createToy(requestToy);

        assertThat(returnedToy).isNotNull();
        assertThat(returnedToy.getName()).isEqualTo(inputToy.getName());
    }

    @Test
    public void givenToy_whenCreate_thenAssignId() {
        Toy inputToy = ToyTest.toy();
        inputToy.setId(0L);
        Toy outputToy = ToyTest.toy();

        Mockito.when(toyRepository.save(any(Toy.class))).thenReturn(outputToy);

        CreateToyRequest requestToy = ToyTest.createToyRequest(inputToy);
        ToyResponse returnedToy = toyService.createToy(requestToy);

        assertThat(returnedToy.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenToy_whenCreate_thenRepositoryCalled() {
        Toy inputToy = ToyTest.toy();
        Toy outputToy = ToyTest.toy();

        Mockito.when(toyRepository.save(any(Toy.class))).thenReturn(outputToy);

        CreateToyRequest requestToy = ToyTest.createToyRequest(inputToy);
        toyService.createToy(requestToy);

        verify(toyRepository, times(1)).save(any(Toy.class));
    }

    @Test
    public void givenToy_whenDelete_thenRepositoryCalled() {
        long id = 1L;

        toyService.deleteToy(id);

        verify(toyRepository, times(1)).deleteById(id);
    }

    @Test
    public void givenItemAndValidId_whenUpdate_thenItemReturned() {
        Toy inputToy = ToyTest.toy();
        inputToy.setId(0L);
        Toy outputToy = ToyTest.toy();
        long id = 1L;

        Mockito.when(toyRepository.findById(id)).thenReturn(Optional.of(outputToy));
        Mockito.when(toyRepository.save(inputToy)).thenReturn(outputToy);

        UpdateToyRequest updateToy = ToyTest.updateToyRequest(inputToy);
        ToyResponse returnedToy = toyService.updateToy(id, updateToy);

        assertThat(returnedToy).isNotNull();
        assertThat(returnedToy.getName()).isEqualTo(inputToy.getName());
        assertThat(returnedToy.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        Toy inputToy = ToyTest.toy();
        UpdateToyRequest updateToy = ToyTest.updateToyRequest(inputToy);
        assertThatThrownBy(() -> toyService.updateToy(2L, updateToy))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    public void givenValidCategoryId_whenGetToyByCategory_thenToysReturned() {
        ArrayList<Toy> toyResponse = new ArrayList<>();
        long id = 1L;
        toyResponse.add(ToyTest.toy());
        Mockito.when(toyRepository.getToysByCategoryId(id)).thenReturn(toyResponse);

        List<ToyResponse> returnedItems = toyService.getToyByCategory(id);

        assertThat(returnedItems).hasSize(1);
    }

    @Test
    public void givenInvalidCategoryId_whenGetToyByCategory_thenNoToysReturned() {
        long id = 1L;
        assertThat(toyService.getToyByCategory(id)).isEmpty();
    }

    @Test
    public void givenValidName_whenGetToyByName_thenToysReturned() {
        String name = "Star Wars";
        ArrayList<Toy> toyResponse = new ArrayList<>();
        toyResponse.add(ToyTest.toy());
        List<SearchToyResponse> returnedItems = toyService.getToyByName(name);
        Mockito.when(toyRepository.getToysByName(name)).thenReturn(returnedItems);

        assertThat(returnedItems).hasSize(1);
    }

    @Test
    public void givenInvalidName_whenGetToysByName_thenNoToysReturned() {
        String name = "Star Wars";
        assertThat(toyService.getToyByName(name)).isEmpty();
    }
}
