package com.toyvalley.services;

import com.toyvalley.models.data.CreateToyRequest;
import com.toyvalley.models.data.ToyResponse;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.repositories.ToyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToyService {
    private final ArrayList<Toy> toyList;
    private final ToyRepository toyRepository;

    public ToyService(ToyRepository toyRepository) {
        this.toyRepository = toyRepository;
        this.toyList = new ArrayList<>();
    }

    public List<ToyResponse> getToy() {
        List<Toy> toys = toyRepository.findAll();
        ArrayList<ToyResponse> responseList = new ArrayList<>();
        for (Toy toy : toys) {
            responseList.add(new ToyResponse(toy.getId() ,toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return responseList;
    }

    public ToyResponse getToy(long id) {
        Optional<Toy> toyOptional = toyRepository.findById(id);

        if (toyOptional.isPresent()) {
            Toy toy = toyOptional.get();
            return new ToyResponse(toy.getId() ,toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased());
        }

        throw new RuntimeException("Item with id " + id + " not found.");
    }

    public ToyResponse createToy(CreateToyRequest toy) {
        // Grab User ID from JWT and use User Repository for fetching user.
        // Save Toy and use newly created toy for Toy Images and Toy Categories
        Toy toyEntity = new Toy(toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDate_purchased());
        Toy newToy = toyRepository.save(toyEntity);
        return new ToyResponse(newToy.getId(), newToy.getName(), newToy.getDescription(), newToy.getBrand(), newToy.getGender(), newToy.getCondition(), newToy.getAge(), newToy.getDatePurchased());
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
