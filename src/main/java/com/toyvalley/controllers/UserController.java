package com.toyvalley.controllers;

import com.toyvalley.models.data.user.CreateUserRequest;
import com.toyvalley.models.data.user.TopTraders;
import com.toyvalley.models.data.user.UpdateUserRequest;
import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.dtos.AuthenticationRequestPayload;
import com.toyvalley.models.entities.User;
import com.toyvalley.models.enums.Status;
import com.toyvalley.repositories.UserRepository;
import com.toyvalley.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    private final UserService userService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        return new ResponseEntity<>(this.userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest user) {
        return new ResponseEntity<>(this.userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable long id) {
      this.userService.deleteUser(id);
      return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.success;
            }
        }
        return Status.failure;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequestPayload loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully.", HttpStatus.OK);
    }

    @GetMapping("/top-traders")
    public ResponseEntity<List<TopTraders>> getTopTraders() {
        List<TopTraders> topTraders = this.userService.getTopTraders();
        return new ResponseEntity<>(topTraders, HttpStatus.OK);
    }
}