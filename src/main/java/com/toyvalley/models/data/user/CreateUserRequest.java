package com.toyvalley.models.data.user;

import com.toyvalley.models.entities.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
  private String name;
  private String surname;
  private String phone;
  private String address;
  private City city;
  private String email;
  private String password;
}
