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

    @OneToMany(mappedBy = "preference")
    private List<Preference> preferences;

    @OneToMany(mappedBy = "category")
    private List<ToyCategory> toyCategories;

    public Category(long id, CategoryName name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(CategoryName name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(CategoryName name, String description) {
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


