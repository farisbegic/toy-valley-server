package com.toyvalley.repositories;

import com.toyvalley.models.entities.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyRepository extends JpaRepository<Toy, Long> {
    @Query("SELECT DISTINCT t FROM Toy t, ToyCategory tc WHERE t = tc.toy AND tc.category.id = :categoryId")
    List<Toy> getToysByCategoryId(@Param("categoryId") long categoryId);
}
