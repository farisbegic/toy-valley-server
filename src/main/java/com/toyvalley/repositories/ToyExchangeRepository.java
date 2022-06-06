package com.toyvalley.repositories;

import com.toyvalley.models.data.toy.SearchToyResponse;
import com.toyvalley.models.data.toyExchange.ToyExchangeResponse;
import com.toyvalley.models.entities.City;
import com.toyvalley.models.entities.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyExchangeRepository extends JpaRepository<ExchangeRequest, Long> {

}
