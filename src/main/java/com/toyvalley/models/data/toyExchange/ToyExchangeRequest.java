package com.toyvalley.models.data.toyExchange;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToyExchangeRequest {
    private long toyOffered;
    private long toyRequested;
    private String message;
}
