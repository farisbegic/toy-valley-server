package com.toyvalley.services;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.entities.City;
import com.toyvalley.models.entities.User;
import com.toyvalley.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
