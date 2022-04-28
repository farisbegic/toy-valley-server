package com.toyvalley.repositories;

import com.toyvalley.models.entities.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {
}
