package com.toyvalley.controllers;

import com.toyvalley.models.data.toy.*;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import com.toyvalley.services.ToyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toys")
public class ToyController {
    private final ToyService toyService;

    public ToyController(ToyService toyService) {
        this.toyService = toyService;
    }

    @GetMapping
    public ResponseEntity<List<ToyResponse>> getToy() {
        return new ResponseEntity<>(this.toyService.getToy(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToyDetailsResponse> getToy(@PathVariable long id) {
        return new ResponseEntity<>(this.toyService.getToy(id), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ToyResponse>> getToyByCategoryId(@PathVariable long categoryId) {
        return new ResponseEntity<>(this.toyService.getToyByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<ToyResponse>> getToyByCityId(@PathVariable long cityId) {
        return new ResponseEntity<>(this.toyService.getToyByCity(cityId), HttpStatus.OK);
    }

    @GetMapping("/gender/{genderId}")
    public ResponseEntity<List<ToyResponse>> getToyByGender(@PathVariable String genderId) {
        return new ResponseEntity<>(this.toyService.getToyByGender(genderId), HttpStatus.OK);
    }

    @GetMapping("/condition/{conditionId}")
    public ResponseEntity<List<ToyResponse>> getToyByCondition(@PathVariable String conditionId) {
        return new ResponseEntity<>(this.toyService.getToyByCondition(conditionId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ToyResponse>> getToyByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(this.toyService.getActiveToysByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/search/{toyName}")
    public ResponseEntity<List<SearchToyResponse>> getToyBySearchName(@PathVariable String toyName) {
        return new ResponseEntity<>(this.toyService.getToyByName(toyName), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ToyResponse> createToy(@PathVariable long userId, @RequestBody CreateToyRequest toy) {
      return new ResponseEntity<>(this.toyService.createToy(userId, toy), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToyResponse> updateToy(@PathVariable long id, @RequestBody UpdateToyRequest toy) {
        ToyResponse response = this.toyService.updateToy(id, toy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToy(@PathVariable long id) {
        this.toyService.deleteToy(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
