package com.toyvalley.controllers;

import com.toyvalley.models.data.toyExchange.ToyExchangeRequest;
import com.toyvalley.models.data.toyExchange.ToyExchangeResponse;
import com.toyvalley.services.ToyExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toy-exchange")
public class ToyExchangeController {

  private final ToyExchangeService toyExchangeService;

  public ToyExchangeController(ToyExchangeService toyExchangeService) {
    this.toyExchangeService = toyExchangeService;
  }

  @PostMapping
  public ResponseEntity<ToyExchangeResponse> createToyExchange(@RequestBody ToyExchangeRequest toyExchangeRequest) {
    return new ResponseEntity<>(this.toyExchangeService.createToyExchange(toyExchangeRequest), HttpStatus.OK);
  }
}
