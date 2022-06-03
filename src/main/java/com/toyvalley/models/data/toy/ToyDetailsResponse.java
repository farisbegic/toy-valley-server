package com.toyvalley.models.data.toy;

import com.toyvalley.models.data.user.UserResponse;
import com.toyvalley.models.enums.Condition;
import com.toyvalley.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToyDetailsResponse {
    private long id;
    private String name;
    private String description;
    private String brand;
    private Gender gender;
    private Condition condition;
    private int age;
    private Date datePurchased;
    private UserResponse user;
    private List<String> preferences;
}
