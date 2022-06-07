package com.toyvalley.models.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IdUser {
    private long id;
    private boolean isAdmin;
}

