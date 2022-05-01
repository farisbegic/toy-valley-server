package com.toyvalley.repositories;

import com.toyvalley.models.entities.Category;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.entities.ToyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyCategoryRepository extends JpaRepository<ToyCategory, Long> {
}
