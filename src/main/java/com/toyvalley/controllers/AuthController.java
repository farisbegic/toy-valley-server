package com.toyvalley.controllers;

import com.toyvalley.models.dtos.AuthenticationRequestPayload;
import com.toyvalley.models.dtos.AuthenticationResponsePayload;
import com.toyvalley.models.entities.User;
import com.toyvalley.models.enums.UserRole;
import com.toyvalley.services.UserService;
import com.toyvalley.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

 private final AuthenticationManager authenticationManager;
 private final JwtUtil jwtTokenUtil;
 private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }


    @PostMapping("/authenticate")
 public ResponseEntity<AuthenticationResponsePayload> createAuthenticationToken(@RequestBody AuthenticationRequestPayload payload) {
    try {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));
    } catch (AuthenticationException e) {
        e.printStackTrace();
       throw new RuntimeException("Error authenticating!");
    }

    final String jwt = jwtTokenUtil.generateToken(payload.getEmail());
    User user = userService.getUser(payload.getEmail());

    return ResponseEntity.ok(new AuthenticationResponsePayload(jwt, user.getId(), user.getAdmin() == UserRole.admin));
   }
}
