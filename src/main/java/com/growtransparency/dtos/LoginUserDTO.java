package com.growtransparency.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record LoginUserDTO(
        @NotBlank(message = "Campo email é obrigatório")
        @Email(message = "E-mail informado não é válido") String email,
        @NotBlank(message = "Campo senha é obrigatório") String password) {
  public UsernamePasswordAuthenticationToken convert() {
    return new UsernamePasswordAuthenticationToken(email, password);
  }
}
