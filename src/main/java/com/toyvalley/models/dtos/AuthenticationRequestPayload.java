package com.toyvalley.models.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestPayload {
    private String email;
    private String password;
}


