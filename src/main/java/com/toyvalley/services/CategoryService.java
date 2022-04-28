package com.toyvalley.services;

import com.toyvalley.models.data.category.CategoryResponse;
import com.toyvalley.models.data.category.CreateCategoryRequest;
import com.toyvalley.models.data.category.UpdateCategoryRequest;
import com.toyvalley.models.entities.Category;
import com.toyvalley.models.enums.CategoryName;
import com.toyvalley.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final List<Category> categoriesList;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoriesList = new ArrayList<>();
        categoriesList.add(generateVehicleCategory());
    }

        public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        ArrayList<CategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            responseList.add(new CategoryResponse(category.getId(), category.getName(), category.getDescription()));

        }

        return responseList;
    }


    public CategoryResponse getCategory(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
        }

        throw new RuntimeException("Category not found provided id:" + id);
    }


    public CategoryResponse createCategory(CreateCategoryRequest category) {
        Category categoryEntity = new Category(category.getName(), category.getDescription());
        Category newCategory = categoryRepository.save(categoryEntity);
        return new CategoryResponse(newCategory.getId(), newCategory.getName(), newCategory.getDescription());
    }

    public CategoryResponse updateCategory(long id, UpdateCategoryRequest category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category categoryEntity = categoryOptional.get();
            categoryEntity.update(category.getName(), category.getDescription());
            categoryRepository.save(categoryEntity);
            return new CategoryResponse(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
        }


        throw new RuntimeException("Category with id " + id + " not found.");
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    private Category generateVehicleCategory() {
        Category category = new Category();
        category.setId(1);

        category.setName(CategoryName.vehicles);

        category.setDescription("Vehicles are one of the most popular categories for boys.");
        return category;
    }
}
