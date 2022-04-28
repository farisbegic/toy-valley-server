package com.toyvalley.models.data.category;
import com.toyvalley.models.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CreateCategoryRequest {
    private CategoryName name;
    private String description;
}
