package com.toyvalley.models.entities;

import com.toyvalley.models.enums.Condition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy {
    private long id;
    private String brand;
    private String model;
    private Condition condition;

    public void update(Toy toy) {
        this.brand = toy.brand;
        this.model = toy.model;
        this.condition = toy.condition;
    }
}
