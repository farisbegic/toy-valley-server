package com.toyvalley.models.data.category;
import com.toyvalley.models.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CategoryResponse {
    private long id;
    private CategoryName name;
    private String description;
}
