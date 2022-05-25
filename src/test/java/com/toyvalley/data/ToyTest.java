package com.toyvalley.data;

import com.toyvalley.models.data.toy.CreateToyRequest;
import com.toyvalley.models.data.toy.UpdateToyRequest;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ToyTest {
    public static Toy toy() {
        return new Toy(1L, "Star Wars", "A great toy", "Lego", Gender.unisex, Condition.brandNew, 0, new Date());
    }
    public static CreateToyRequest createToyRequest(Toy inputToy) {
        return new CreateToyRequest(inputToy.getName(), inputToy.getDescription(), inputToy.getBrand(),inputToy.getGender(), inputToy.getCondition(),inputToy.getAge(), inputToy.getDatePurchased());
    }
    public static UpdateToyRequest updateToyRequest(Toy inputToy) {
        return new UpdateToyRequest(inputToy.getName(), inputToy.getDescription(), inputToy.getBrand(), inputToy.getGender(), inputToy.getCondition(), inputToy.getAge(), inputToy.isActive(), inputToy.getDatePurchased());
    }
}
