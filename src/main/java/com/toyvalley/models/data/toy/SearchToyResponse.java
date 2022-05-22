package com.toyvalley.models.data.toy;

import lombok.Data;

@Data
public class SearchToyResponse {
    private long id;
    private String name;

    public SearchToyResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
