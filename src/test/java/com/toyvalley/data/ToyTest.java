package com.toyvalley.data;

import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;

import java.util.Date;

public class ToyTest {
    public static Toy toy() {
        return new Toy("Star Wars", "A great toy", "Lego", Gender.unisex, Condition.brandNew, 0, new Date());
    }
}
