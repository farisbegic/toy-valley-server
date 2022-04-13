package com.toyvalley.services;

import com.toyvalley.models.Toy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToyService {
    private final ArrayList<Toy> toyList;

    public ToyService() {
        this.toyList = new ArrayList<>();
    }

    public List<Toy> getToy() {
        return this.toyList;
    }

    public Toy getToy(long id) {
        for (Toy toy : toyList) {
            if (toy.getId() == id) {
                return toy;
            }
        }
        return null;
    }

    public Toy createToy(Toy toy) {
        long id = toyList.size() + 1;
        toy.setId(id);
        toyList.add(toy);
        return toy;
    }

    public Toy updateToy(long id, Toy toy) {
        for (Toy toyInstance : toyList) {
            if (toyInstance.getId() == id) {
                toyInstance.update(toy);
                return toyInstance;
            }
        }
        return null;
    }

    public boolean deleteToy(long id) {
        return toyList.removeIf(toy -> toy.getId() == id);
    }
}
