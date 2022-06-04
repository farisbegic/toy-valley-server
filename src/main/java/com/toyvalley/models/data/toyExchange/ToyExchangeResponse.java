package com.toyvalley.models.data.toyExchange;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToyExchangeResponse {
    private long toyOffered;
    private long toyRequested;
}
