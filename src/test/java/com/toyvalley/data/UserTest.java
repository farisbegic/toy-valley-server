package com.toyvalley.data;

import com.toyvalley.models.data.user.CreateUserRequest;
import com.toyvalley.models.data.user.UpdateUserRequest;
import com.toyvalley.models.entities.City;
import com.toyvalley.models.entities.User;

public class UserTest {

  public static User user() {
    User user = new User();
    user.setId(1L);
    user.setName("Azra");
    user.setSurname("Kurtic");
    user.setPhone("123456");
    user.setAddress("Address 1");
    City city = new City();
    city.setId(1L);
    city.setName("Sarajevo");
    user.setCity(city);
    user.setEmail("azra@gmail.com");
    user.setPassword("Pass123456");
    return user;
  }

  public static CreateUserRequest createUserRequest(User inputUser) {
    return new CreateUserRequest(inputUser.getName(), inputUser.getSurname(), inputUser.getPhone(), inputUser.getAddress(), inputUser.getCity(), inputUser.getEmail(), inputUser.getPassword());
  }

  public static UpdateUserRequest updateUserRequest(User inputUser) {
    return new UpdateUserRequest(inputUser.getName(), inputUser.getSurname(), inputUser.getPhone(), inputUser.getAddress(), inputUser.getEmail(), inputUser.getPassword(), inputUser.getCity(), inputUser.isActive());
  }

}
