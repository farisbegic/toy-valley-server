package com.toyvalley.models.data.toyExchange;

import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.data.toy.ToyResponseExchange;
import com.toyvalley.models.entities.Toy;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRequestsResponse {
  ToyResponseExchange toy_offered;
  ToyResponseExchange toy_requested;
  boolean active;
  String message;


}
