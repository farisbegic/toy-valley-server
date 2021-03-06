package com.toyvalley.services;

import com.toyvalley.models.data.city.CityResponse;
import com.toyvalley.models.data.user.CreateUserRequest;
import com.toyvalley.models.data.user.TopTraders;
import com.toyvalley.models.data.user.UpdateUserRequest;
import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.entities.User;
import com.toyvalley.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getUsers() {
      List<User> users = userRepository.findAll();
      ArrayList<UserResponse> responseList = new ArrayList<>();

      for (User user : users) {
        responseList.add(new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), new CityResponse(user.getCity().getId(), user.getCity().getName()), user.getEmail()));
      }

      return responseList;
    }

    public UserResponse getUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
          User user = userOptional.get();
          return new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), new CityResponse(user.getCity().getId(), user.getCity().getName()), user.getEmail());
        }
        throw new RuntimeException("User with id " + id + " is not found");
    }

    public User getUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException("User with email " + email + " is not found");
    }

    public UserResponse createUser(CreateUserRequest user) {
      User userEntity = new User(user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), user.getCity(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
      User newUser = userRepository.save(userEntity);
      return new UserResponse(newUser.getId(), newUser.getName(), newUser.getSurname(), newUser.getPhone(), newUser.getAddress(), new CityResponse(newUser.getCity().getId(), newUser.getCity().getName()), newUser.getEmail());
    }


    public UserResponse updateUser(long id, UpdateUserRequest user) {

      Optional<User> userOptional = userRepository.findById(id);

      if (userOptional.isPresent()) {
        User userEntity = userOptional.get();
        userEntity.update(user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), user.getCity(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
        return new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getSurname(), userEntity.getPhone(), userEntity.getAddress(), new CityResponse(userEntity.getCity().getId(), userEntity.getCity().getName()), userEntity.getEmail());
      }

      throw new RuntimeException("User with id " + id + " is not found");
    }

    public void deleteUser(long id) {
      userRepository.deleteById(id);
    }

    public List<TopTraders> getTopTraders() {
        List<TopTraders> topTradersList = userRepository.findTopTraders();
        int limit = (Math.min(topTradersList.size(), 10));
        return topTradersList.subList(0, limit);
    }
}
