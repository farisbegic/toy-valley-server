package com.toyvalley.models.data.category;
import com.toyvalley.models.enums.CategoryName;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryTest {

    public static Category category() {
        Category category = new Category();

        category.setId(5L);
        category.setName(CategoryName.educational);
        category.setDescription("This is just a testing category.");

        return category;
    }
}