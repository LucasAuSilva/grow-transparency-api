package com.growtransparency.dtos;

import com.growtransparency.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank(message = "É necessário inserir o nome") String name,
        @NotBlank(message = "É necessário inserir o sobrenome") String lastName,
        @NotBlank(message = "É necessário inserir o e-mail")
        @Email(message = "E-mail informado não é válido") String email,
        @NotBlank(message = "É necessário inserir a senha")
        @Size(min = 8, message = "A senha deverá conter no mínimo 8 caracteres") String password) {
  public User toEntity() {
    return new User(name, lastName, email, password);
  }
}
