package data;

import com.toyvalley.models.data.user.CreateUserRequest;
import com.toyvalley.models.data.user.UserResponse;
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
}
