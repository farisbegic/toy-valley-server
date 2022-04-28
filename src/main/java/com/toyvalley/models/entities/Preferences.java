package com.toyvalley.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preferences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    User user;

    @ManyToOne
    Category category;
}
