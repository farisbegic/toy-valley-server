package com.toyvalley.repositories;

import com.toyvalley.models.entities.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToyExchangeRepository extends JpaRepository<ExchangeRequest, Long> {

  @Query(value = "SELECT er.* FROM exchange_request er, toy t WHERE er.toy_requested_id = t.id AND t.user_id = :userId" +
    " AND er.active = true", nativeQuery = true)
  List<ExchangeRequest> getUserExchangeRequests(@Param("userId") long userId);
}
