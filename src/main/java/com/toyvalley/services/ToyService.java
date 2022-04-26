package com.toyvalley.services;

import com.toyvalley.models.entities.Toy;
import org.springframework.stereotype.Service;

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
