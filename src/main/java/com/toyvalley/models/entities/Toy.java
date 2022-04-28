package com.toyvalley.models.entities;

import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "toy")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "condition")
    @Enumerated(value = EnumType.STRING)
    private Condition condition;

    @Column(name = "age")
    private int age;

    @Column(name = "date_purchased")
    private Date datePurchased;

    @Column(name = "date_posted")
    private Date datePosted;

    @Column(name = "active")
    private boolean isActive;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "toy")
    private List<ToyCategory> toyCategories;

    @OneToMany(mappedBy = "toy_offered")
    private List<ExchangeRequest> toyOffers;

    @OneToMany(mappedBy = "toy_requested")
    private List<ExchangeRequest> toyRequests;

    @OneToMany(mappedBy = "toy")
    private List<ToyImage> toyImages;

    public Toy(String name, String description, String brand, Gender gender, Condition condition, int age, Date datePurchased) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.gender = gender;
        this.condition = condition;
        this.age = age;
        this.datePurchased = datePurchased;
    }

    public void update(Toy toy) {
        this.name = toy.name;
        this.brand = toy.brand;
        this.gender = toy.gender;
        this.condition = toy.condition;
        this.age = toy.age;
        this.datePurchased = toy.datePurchased;
        this.datePosted = toy.datePosted;
        this.isActive = toy.isActive;
        this.description = toy.description;
    }
}
