package com.toyvalley.models.dtos;

import com.toyvalley.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponsePayload {
  private String token;
  private long userId;
  private boolean isAdmin;
}
