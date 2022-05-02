package com.toyvalley.services;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.entities.City;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

  private final CityRepository cityRepository;

  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  public List<CityResponse> getCities() {
    List<City> cities = cityRepository.findAll();
    ArrayList<CityResponse> responseList = new ArrayList<>();

    for (City city : cities) {
      responseList.add(new CityResponse(city.getId(), city.getName()));
    }

    return responseList;
  }

  public CityResponse getCity(long id) {
    Optional<City> cityOptional = cityRepository.findById(id);
    if (cityOptional.isPresent()) {
      City city = cityOptional.get();
      return new CityResponse(city.getId(), city.getName());
    }

    throw new RuntimeException("City with id " + id + " not found.");
  }
}
