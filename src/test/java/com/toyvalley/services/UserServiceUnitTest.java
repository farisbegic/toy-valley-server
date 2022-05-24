package com.toyvalley.services;

import com.toyvalley.models.data.user.CreateUserRequest;
import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.entities.User;
import com.toyvalley.repositories.UserRepository;
import data.UserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {
  @MockBean
  private UserRepository userRepository;

  @TestConfiguration
  static class ItemServiceTestContextConfiguration {

    @Bean
    @Primary
    public UserService userService(UserRepository userRepository) {
      return new UserService(userRepository);
    }
  }

  @Autowired
  private UserService userService;

  @Test
  public void givenUsers_whenGetUsers_thenListShouldBeFound() {
    Mockito.when(userRepository.findAll())
      .thenReturn(List.of(UserTest.user()));

    List<UserResponse> returnedList = userService.getUsers();

    assertThat(returnedList).hasSize(1);
  }

  @Test
  public void givenValidId_whenGetById_thenUserShouldBeFound() {
    User user = UserTest.user();

    Mockito.when(userRepository.findById(user.getId()))
      .thenReturn(Optional.of(user));

    UserResponse resultUser = userService.getUser(user.getId());

    assertThat(resultUser.getName())
      .isEqualTo(user.getName());
  }

  
}
