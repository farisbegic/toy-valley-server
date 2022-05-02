package com.toyvalley.controllers;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping
  public ResponseEntity<List<CityResponse>> getCities() {
    return new ResponseEntity<>(this.cityService.getCities(), HttpStatus.OK);
  }
}
