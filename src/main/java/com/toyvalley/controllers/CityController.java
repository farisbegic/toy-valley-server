package com.toyvalley.controllers;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.city.CreateCityRequest;
import com.toyvalley.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public ResponseEntity<CityResponse> getCity(@PathVariable long id) {
    return new ResponseEntity<>(this.cityService.getCity(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCity(@PathVariable long id) {
    this.cityService.deleteCity(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CityResponse> createCity(@RequestBody CreateCityRequest city) {
    return new ResponseEntity<>(this.cityService.createCity(city), HttpStatus.OK);
  }
}
