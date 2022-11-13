package com.growtransparency.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginUserDTO(String email, String password) {
  public UsernamePasswordAuthenticationToken convert() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
