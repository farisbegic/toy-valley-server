package com.toyvalley.controllers;

import com.toyvalley.models.Toy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/toys")
public class ToyController {
    private final ArrayList<Toy> toyList;

    public ToyController() {
        this.toyList = new ArrayList<>();
    }

    @GetMapping
    public List<Toy> getToy() {
        return this.toyList;
    }

    @GetMapping("{/id}")
    public Toy getToy(@PathVariable long id) {
        for (Toy toy : toyList) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    @PostMapping
    public Toy createToy(@RequestBody Toy toy) {
        long id = toyList.size() + 1;
        toy.setId(id);
        toyList.add(toy);
        return toy;
    }

    @PutMapping("/{id}")
    Toy updateToy(@PathVariable long id, @RequestBody Toy toy) {
        for (Toy toyInstance : toyList) {
            if (toyInstance.getId() == id) {
                toyInstance.update(toy);
                return toyInstance;
            }
        }
        return null;
    }
}
