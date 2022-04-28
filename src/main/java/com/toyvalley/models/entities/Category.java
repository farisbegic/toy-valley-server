package com.toyvalley.models.entities;

import com.toyvalley.models.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Enumerated(value = EnumType.STRING)
    private CategoryName name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Preferences> preferences;

    @OneToMany(mappedBy = "category")
    private List<ToyCategory> toyCategories;

    public void update(Category category) {
        this.name = category.name;
        this.description = category.description;
    }
}


