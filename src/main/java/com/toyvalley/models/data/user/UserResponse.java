package com.toyvalley.models.data.user;

import com.toyvalley.models.data.city.CityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
  private long id;
  private String name;
  private String surname;
  private String phone;
  private String address;
  private CityResponse city;
  private String email;
  private String password;
}
