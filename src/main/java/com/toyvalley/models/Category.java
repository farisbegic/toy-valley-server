package com.toyvalley.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private long id;
    private CategoryName categoryName;
    private String description;

    public void update(Category category) {
        this.categoryName = category.categoryName;
        this.description = category.description;

    }

}


