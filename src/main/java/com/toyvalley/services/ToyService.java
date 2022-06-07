package com.toyvalley.services;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.toy.*;
import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.entities.City;
import com.toyvalley.models.entities.Preference;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import com.toyvalley.models.entities.User;
import com.toyvalley.repositories.CityRepository;
import com.toyvalley.repositories.ToyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ToyService {
    private final ToyRepository toyRepository;
    private final CityRepository cityRepository;

    public ToyService(ToyRepository toyRepository, CityRepository cityRepository) {
        this.toyRepository = toyRepository;
        this.cityRepository = cityRepository;
    }

    public List<ToyResponse> getToy() {
        List<Toy> toys = toyRepository.findAll();
        ArrayList<ToyResponse> responseList = new ArrayList<>();

        for (Toy toy : toys) {
            responseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }

        return responseList;
    }

    public ToyDetailsResponse getToy(long id) {
        Optional<Toy> toyOptional = toyRepository.findById(id);

        if (toyOptional.isPresent()) {
            Toy toy = toyOptional.get();

            User toyUser = toy.getUser();
            City toyCity = toy.getUser().getCity();

            CityResponse cityResponse = new CityResponse(toyCity.getId(), toyCity.getName());
            UserResponse userResponse = new UserResponse(toyUser.getId(), toyUser.getName(), toyUser.getSurname(), toyUser.getPhone(), toyUser.getAddress(), cityResponse, toyUser.getEmail());
            List<String> preferences = new ArrayList<>();

            for (Preference p : toy.getUser().getPreferences()) {
                preferences.add(String.valueOf(p.getCategory().getName()));
            }

            return new ToyDetailsResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased(), userResponse, preferences);
        }

        throw new RuntimeException("Item with id " + id + " not found.");
    }

    public ToyResponse createToy(CreateToyRequest toy) {
        Toy toyEntity = new Toy(toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDate_purchased());
        Toy newToy = toyRepository.save(toyEntity);
        return new ToyResponse(newToy.getId(), newToy.getName(), newToy.getDescription(), newToy.getBrand(), newToy.getGender(), newToy.getCondition(), newToy.getAge(), newToy.getDatePurchased());
    }

    public ToyResponse updateToy(long id, UpdateToyRequest toy) {
        Optional<Toy> toyOptional = toyRepository.findById(id);

        if (toyOptional.isPresent()) {
            Toy toyEntity = toyOptional.get();
            toyEntity.update(toy.getName(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDate_purchased(), toy.is_active(), toy.getDescription());
            toyRepository.save(toyEntity);
            return new ToyResponse(toyEntity.getId(), toyEntity.getName(), toyEntity.getDescription(), toyEntity.getBrand(), toyEntity.getGender(), toyEntity.getCondition(), toyEntity.getAge(), toyEntity.getDatePurchased());
        }

        throw new RuntimeException("Item with id " + id + " not found.");
    }

    public void deleteToy(long id) {
        toyRepository.deleteById(id);
    }

    public List<ToyResponse> getToyByCategory(long categoryId) {
        ArrayList<ToyResponse> toyResponseList = new ArrayList<>();
        List<Toy> toysList = toyRepository.getToysByCategoryId(categoryId);
        for (Toy toy : toysList) {
            toyResponseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return toyResponseList;
    }

    public List<ToyResponse> getToyByCity(long cityId) {
        ArrayList<ToyResponse> toyResponseList = new ArrayList<>();
        List<Toy> toysList = toyRepository.findToysByUser_CityId(cityId);

        for (Toy toy : toysList) {
            toyResponseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return toyResponseList;
    }

    public List<ToyResponse> getToyByUser(long userId) {
        ArrayList<ToyResponse> toyResponseList = new ArrayList<>();
        List<Toy> toysList = toyRepository.findToysByUserId(userId);

        for (Toy toy : toysList) {
            toyResponseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return toyResponseList;
    }

    public List<ToyResponse> getToyByGender(Gender gender) {
        ArrayList<ToyResponse> toyResponseList = new ArrayList<>();
        List<Toy> toysList = toyRepository.getToyByGender(gender);

        for (Toy toy : toysList) {
            toyResponseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return toyResponseList;
    }

    public List<ToyResponse> getToyByCondition(Condition condition) {
        ArrayList<ToyResponse> toyResponseList = new ArrayList<>();
        List<Toy> toysList = toyRepository.getToyByCondition(condition);

        for (Toy toy : toysList) {
            toyResponseList.add(new ToyResponse(toy.getId(), toy.getName(), toy.getDescription(), toy.getBrand(), toy.getGender(), toy.getCondition(), toy.getAge(), toy.getDatePurchased()));
        }
        return toyResponseList;
    }

    public List<SearchToyResponse> getToyByName(String name) {
        List<SearchToyResponse> toyResponseList = toyRepository.getToysByName(name);
        return toyResponseList;
    }
}
