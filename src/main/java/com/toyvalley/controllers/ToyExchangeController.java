package com.toyvalley.controllers;

import com.toyvalley.models.data.category.CategoryResponse;
import com.toyvalley.models.data.category.CreateCategoryRequest;
import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.data.toyExchange.ToyExchangeRequest;
import com.toyvalley.models.data.toyExchange.ToyExchangeResponse;
import com.toyvalley.services.CityService;
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

  @PostMapping
  public ResponseEntity<ToyExchangeResponse> createToyExchange(@RequestBody ToyExchangeRequest toyExchangeRequest) {
    return new ResponseEntity<>(this.toyExchangeService.createToyExchange(toyExchangeRequest), HttpStatus.OK);
  }

  /*@GetMapping("/user/{userId}")
  public ResponseEntity<List<ToyExchangeResponse>> getExchangeRequests(@PathVariable long userId) {
    return new ResponseEntity<>(this.toyExchangeService.getExchangeRequests(userId), HttpStatus.OK);
  }*/

}
