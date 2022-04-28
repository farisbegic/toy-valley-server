package com.toyvalley.models.data.toy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UpdateToyRequest {
    private String name;
    private String description;
    private String brand;
    private Gender gender;
    private Condition condition;
    private int age;
    private boolean is_active;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    private Date date_purchased;
}
