package com.toyvalley.models.data.category;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CategoryResponse {
    private long id;
    private String name;
    private String description;
}
