package com.toyvalley.models.data.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopTraders {
    private long id;
    private String firstName;
    private String lastName;
    private long trades;
}
