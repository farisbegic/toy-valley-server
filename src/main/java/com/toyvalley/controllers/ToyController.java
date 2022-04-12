package com.toyvalley.controllers;

import com.toyvalley.models.Toy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/toys")
public class ToyController {
    private final ArrayList<Toy> toyList;

    public ToyController() {
        this.toyList = new ArrayList<>();
    }

    @GetMapping
    public ResponseEntity<List<Toy>> getToy() {
        return new ResponseEntity<>(this.toyList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Toy> getToy(@PathVariable long id) {
        for (Toy toy : toyList) {
            if (toy.getId() == id) {
                return new ResponseEntity<>(toy, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Toy> createToy(@RequestBody Toy toy) {
        long id = toyList.size() + 1;
        toy.setId(id);
        toyList.add(toy);
        return new ResponseEntity<>(toy, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Toy> updateToy(@PathVariable long id, @RequestBody Toy toy) {
        for (Toy toyInstance : toyList) {
            if (toyInstance.getId() == id) {
                toyInstance.update(toy);
                return new ResponseEntity<>(toyInstance, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteToy(@PathVariable long id) {
        var isRemoved = toyList.removeIf(toy -> toy.getId() == id);

        if (isRemoved) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
