package com.toyvalley.repositories;

import com.toyvalley.models.data.toy.SearchToyResponse;
import com.toyvalley.models.entities.Category;
import com.toyvalley.models.entities.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
