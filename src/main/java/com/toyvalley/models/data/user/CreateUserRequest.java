package com.toyvalley.models.data.user;

import com.toyvalley.models.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequest {
  private String name;
  private String surname;
  private String phone;
  private String address;
  private City city;
  private String email;
  private String password;
}
