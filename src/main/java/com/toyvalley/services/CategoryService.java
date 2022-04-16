package com.toyvalley.services;

import com.toyvalley.models.Category;
import com.toyvalley.models.CategoryName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final List<Category> categoriesList;

    public CategoryService() {

        categoriesList = new ArrayList<>();
        categoriesList.add(generateVehicleCategory());
    }

    public List<Category> getCategories() {
        return categoriesList;
    }

    public Category getCategory(long id) {
        for (Category category : categoriesList) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new RuntimeException("Category not found provided id:" + id);
    }

    public Category createCategory(Category category) {
        long id = categoriesList.size() + 1;
        category.setId(id);
        categoriesList.add(category);
        return category;
    }

    public Category updateCategory(long id, Category category) {
        for (Category categoryInstance : categoriesList) {
            if (categoryInstance.getId() == id) {
                categoryInstance.update(category);
                return categoryInstance;
            }
        }
        return null;
    }

    public boolean deleteCategory(long id) {
        return categoriesList.removeIf(category -> category.getId() == id);
    }

    private Category generateVehicleCategory() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName(CategoryName.vehicles);
        category.setDescription("Vehicles are one of the most popular categories for boys.");
        return category;
    }
}
