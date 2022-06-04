package com.toyvalley.models.entities;

import com.toyvalley.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

  @Column(name = "logged_in")
  private boolean loggedIn;

  @Column(name = "phone")
  private String phone;

  @Column(name = "address")
  private String address;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "active")
  private boolean active;

  @ManyToOne
  private City city;

  @OneToMany(mappedBy = "user")
  private List<Preference> preferences;

  @OneToMany(mappedBy = "user")
  private List<Toy> toys;

  public boolean isLoggedIn() {
    return loggedIn;
  }
  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  @Column(name = "admin")
  private UserRole admin;

  public User(String name, String surname, String phone, String address , City city, String email, String password) {
    this.name = name;
    this.surname = surname;
    this.loggedIn = false;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.email = email;
    this.password = password;
    this.active = true;
    this.admin = UserRole.user;
  }

  public void update(String name, String surname, String phone, String address, City city, String email, String password) {
    this.name = name;
    this.surname = surname;
    this.loggedIn = false;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.email = email;
    this.password = password;
    this.active = active;
    }
}
