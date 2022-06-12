package com.toyvalley.models.data.toyExchange;

import com.toyvalley.models.data.toy.ToyResponseExchange;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRequestsResponse {
  long id;
  ToyResponseExchange toy_offered;
  ToyResponseExchange toy_requested;
  boolean active;
  String message;
}
