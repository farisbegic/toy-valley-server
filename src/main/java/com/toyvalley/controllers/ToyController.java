package com.toyvalley.controllers;

import com.toyvalley.models.Toy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/toys")
public class ToyController {
    private ArrayList<Toy> toyList;

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
}
