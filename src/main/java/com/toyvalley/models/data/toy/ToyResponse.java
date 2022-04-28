package com.toyvalley.models.data.toy;

import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ToyResponse {
    private long id;
    private String name;
    private String description;
    private String brand;
    private Gender gender;
    private Condition condition;
    private int age;
    private Date date_purchased;
}
