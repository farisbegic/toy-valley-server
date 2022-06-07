package com.toyvalley.repositories;


import com.toyvalley.models.entities.Category;
import com.toyvalley.models.data.category.SearchCategoryResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {



    @Query("SELECT new com.toyvalley.models.data.category.SearchCategoryResponse(c.id, c.name) FROM Category c WHERE upper(c.name) LIKE upper(concat('%', :name, '%'))")
    List<SearchCategoryResponse> getCategoriesByName(@Param("name") String name);

}
