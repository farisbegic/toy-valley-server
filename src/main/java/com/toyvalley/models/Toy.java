package com.toyvalley.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy {
    private long id;
    private String brand;
    private String model;
    private Condition condition;
}
