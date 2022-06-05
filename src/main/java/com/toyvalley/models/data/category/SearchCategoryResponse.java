package com.toyvalley.models.data.category;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCategoryResponse {
    private long id;
    private String name;
}