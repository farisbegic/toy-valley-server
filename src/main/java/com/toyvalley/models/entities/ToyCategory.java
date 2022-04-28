package com.toyvalley.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "toy_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToyCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    Toy toy;

    @ManyToOne
    Category category;
}
