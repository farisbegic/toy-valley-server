package com.toyvalley.controllers;

import com.toyvalley.models.Toy;
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
    public ResponseEntity<List<Toy>> getToy() {
        return new ResponseEntity<>(this.toyService.getToy(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toy> getToy(@PathVariable long id) {
        return new ResponseEntity<>(this.toyService.getToy(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Toy> createToy(@RequestBody Toy toy) {
        Toy response = this.toyService.createToy(toy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Toy> updateToy(@PathVariable long id, @RequestBody Toy toy) {
        Toy response = this.toyService.updateToy(id, toy);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteToy(@PathVariable long id) {
        if (this.toyService.deleteToy(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
