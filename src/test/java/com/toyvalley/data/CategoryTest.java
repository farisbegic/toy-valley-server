package com.toyvalley.data;
import com.toyvalley.models.data.category.CreateCategoryRequest;
import com.toyvalley.models.data.category.UpdateCategoryRequest;
import com.toyvalley.models.entities.Category;
import com.toyvalley.models.enums.CategoryName;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryTest  {
    public static Category category() {
        return new Category(1L, CategoryName.vehicles, "Very nice car.");
    }
    public static CreateCategoryRequest createCategoryRequest(Category inputCategory) {
        return new CreateCategoryRequest(inputCategory.getName(), inputCategory.getDescription());
    }
    public static UpdateCategoryRequest updateCategoryRequest(Category inputCategory) {
        return new UpdateCategoryRequest(inputCategory.getName(), inputCategory.getDescription());
    }
}

