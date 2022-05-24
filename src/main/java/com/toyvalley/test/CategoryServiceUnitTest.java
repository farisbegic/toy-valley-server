package com.toyvalley.test;

import com.toyvalley.models.data.category.CategoryResponse;
import com.toyvalley.models.entities.Category;
import com.toyvalley.models.enums.CategoryName;
import com.toyvalley.repositories.CategoryRepository;
import com.toyvalley.services.CategoryService;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class CategoryServiceUnitTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @TestConfiguration
    static class CategoryServiceTestContextConfiguration {

        @Bean
        @Primary
        public CategoryService categoryService(CategoryRepository categoryRepository) {
            return new CategoryService(categoryRepository);
        }
    }

    @Autowired
    private CategoryService categoryService;

    @Test
    public void givenCategories_whenGetCategories_thenListShouldBeFound() {
        Mockito.when(categoryRepository.findAll())
                .thenReturn(List.of(CategoryTest.category()));

        List<CategoryResponse> returnedList = categoryService.getCategories();

        assertThat(returnedList).hasSize(1);
    }

    @Test
    public void givenNoCategories_whenGetCategories_thenListShouldBeEmpty() {
        assertThat(categoryService.getCategories()).isEmpty();
    }

    @Test
    public void givenValidId_whenGetById_thenCategoryShouldBeFound() {

        Category category = CategoryTest.category();

        Mockito.when(categoryRepository.findById(category.getId()))
                .thenReturn(Optional.of(category));

        CategoryResponse resultCategory = categoryService.getCategory(category.getId());

        assertThat(resultCategory.getName())
                .isEqualTo(category.getName());
    }

    @Test
    public void givenInvalidId_whenGetById_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> categoryService.getCategory(8L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenCategory_whenCreate_thenIdAssigned() {
        Category inputCategory = CategoryTest.category();
        inputCategory.setId(0L);
        Category outputCategory = CategoryTest.category();

        Mockito.when(categoryRepository.save(inputCategory))
                .thenReturn(outputCategory);

        CategoryResponse resultCategory = categoryService.createCategory(inputCategory);

        assertThat(resultCategory.getId()).isNotEqualTo(0L);
    }

    @Test
    public void givenCategory_whenCreate_thenCategoryReturned() {

        Category inputCategory = CategoryTest.category();
        inputCategory.setId(0L);
        Category outputCategory = CategoryTest.category();

        Mockito.when(categoryRepository.save(inputCategory))
                .thenReturn(outputCategory);

        CategoryResponse resultCategory = categoryService.createCategory(inputCategory);

        assertThat(resultCategory).isNotNull();
        assertThat(resultCategory.getName()).isEqualTo(inputCategory.getName());
    }

    @Test
    public void givenCategory_whenCreate_thenRepositoryCalled() {

        Category category = CategoryTest.category();

        categoryService.createCategory(category);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void givenCategoryAndValidId_whenUpdate_thenCategoryReturned() {

        Category inputCategory = CategoryTest.category();
        inputCategory.setId(0L);
        long id = 5L;
        Category outputCategory = CategoryTest.category();
        outputCategory.setId(id);

        Mockito.when(categoryRepository.findById(id))
                .thenReturn(Optional.of(outputCategory));
        Mockito.when(categoryRepository.save(inputCategory))
                .thenReturn(outputCategory);

        CategoryResponse resultCategory = categoryService.updateCategory(id, inputCategory);


        assertThat(resultCategory).isNotNull();
        assertThat(resultCategory.getName()).isEqualTo(inputCategory.getName());
        assertThat(resultCategory.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenUpdate_thenExceptionShouldBeThrown() {
        assertThatThrownBy(() -> categoryService.updateCategory(5L, CategoryTest.category()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    public void givenCategory_whenDelete_thenRepositoryCalled() {
        long id = 5L;

        categoryService.deleteCategory(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }

    @Test
    public void givenCategory_whenSaved_thenSuccess() {

        Category inputCategory = CategoryTest.category();
        Category outputCategory = CategoryTest.category();

        inputCategory.setId(0L);
        inputCategory.setName(CategoryName.games_and_puzzles);
        inputCategory.setDescription("This is a testing category.");

        Mockito.when(categoryRepository.save(inputCategory)).thenReturn(outputCategory);

        Category savedCategory = categoryRepository.save(inputCategory);

        assertThat(savedCategory.getName()).isNotNull();
    }

    @Test // another way of checking if the category is saved in database
    // similar function as the first test
    public void givenCategory_whenSaved_thenInDatabase() {
        Category inputCategory = CategoryTest.category();

        inputCategory.setId(0L);
        inputCategory.setName(CategoryName.action_figures);
        inputCategory.setDescription("This is a testing category.");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(inputCategory);

        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryResponse> fetchedCategories = categoryService.getCategories();
        assertThat(fetchedCategories.size()).isGreaterThan(0);
    }

    @Test
     void givenNonexistingCategory_whenDeleted_thenExceptionFound() {

        long id = 5L;

        Mockito.when(categoryRepository.findById(id)).thenThrow(new Exception());
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            assertThat(Exception.class).isEqualTo(e.getClass());
        }
    }

    @Test
    void givenExistingCategory_whenDeleted_thenSuccess() {

        Category inputCategory = CategoryTest.category();
        inputCategory.setId(0L);
        long id = 5L;
        Category outputCategory = CategoryTest.category();
        outputCategory.setId(id);

       Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(outputCategory));
            Mockito.doNothing().when(categoryRepository).deleteById(id);
            categoryService.deleteCategory(id);
            Mockito.verify(categoryRepository, times(1)).deleteById(id);
        }
    }
