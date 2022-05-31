package com.toyvalley.models.data.toy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchToyResponse {
    private long id;
    private String name;
}
