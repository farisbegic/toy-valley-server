package com.toyvalley.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "toy_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Toy toy;

    @Column(name = "path")
    private String path;
}
