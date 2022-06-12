package com.toyvalley.controllers;

import com.toyvalley.models.data.toyExchange.ExchangeRequestsResponse;
import com.toyvalley.models.data.toyExchange.ToyExchangeRequest;
import com.toyvalley.models.data.toyExchange.ToyExchangeResponse;
import com.toyvalley.models.data.toyExchange.UpdateExchangeRequest;
import com.toyvalley.services.ToyExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toy-exchange")
public class ToyExchangeController {

  private final ToyExchangeService toyExchangeService;

  public ToyExchangeController(ToyExchangeService toyExchangeService) {
    this.toyExchangeService = toyExchangeService;
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ExchangeRequestsResponse>> getUserExchangeRequests(@PathVariable long userId) {
    return new ResponseEntity<>(this.toyExchangeService.getUserExchangeRequests(userId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ToyExchangeResponse> createToyExchange(@RequestBody ToyExchangeRequest toyExchangeRequest) {
    return new ResponseEntity<>(this.toyExchangeService.createToyExchange(toyExchangeRequest), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExchangeRequestsResponse> updateExchangeRequest(@PathVariable long id, @RequestBody UpdateExchangeRequest updateExchangeRequest) {
    ExchangeRequestsResponse response = this.toyExchangeService.updateExchangeRequest(id, updateExchangeRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
