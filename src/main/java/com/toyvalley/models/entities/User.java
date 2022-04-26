package com.toyvalley.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date dob;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "user")
    private List<Preferences> preferences;

    @OneToMany(mappedBy = "user")
    private List<Toy> toys;

    public void update(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.phone = user.phone;
        this.dob = user.dob;
        this.address = user.address;
        this.email = user.email;
        this.password = user.password;
        this.city = user.city;
    }
}
