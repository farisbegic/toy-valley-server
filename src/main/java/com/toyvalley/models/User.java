package com.toyvalley.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private long id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String city;
    private String email;
    private String password;

    public void update(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.phone = user.phone;
        this.address = user.address;
        this.city = user.city;
        this.email = user.email;
        this.password = user.password;
    }
}
