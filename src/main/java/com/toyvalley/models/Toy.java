package com.toyvalley.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy {
    long id;
    String brand;
    String model;
    Condition condition;
}
