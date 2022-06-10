package com.toyvalley.models.data.toyExchange;

import com.toyvalley.models.entities.Toy;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UpdateExchangeRequest {
  Toy toy_offered;
  Toy toy_requested;
  boolean active;
  String message;
  Date acceptDate;
  Date requestDate;
}
