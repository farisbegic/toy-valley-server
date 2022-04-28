package com.toyvalley.models.data.category;

import com.toyvalley.models.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateCategoryRequest {
    private CategoryName name;
    private String description;
}