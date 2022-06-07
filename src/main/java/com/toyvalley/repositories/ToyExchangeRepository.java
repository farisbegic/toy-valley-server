package com.toyvalley.repositories;

import com.toyvalley.models.entities.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyExchangeRepository extends JpaRepository<ExchangeRequest, Long> {
}
