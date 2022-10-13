package com.growtransparency.dtos;

import com.growtransparency.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserDTO {
  @NotBlank(message = "É necessário inserir o nome")
  private String name;

  @NotBlank(message = "É necessário inserir o sobrenome")
  private String lastName;
  @NotBlank(message = "É necessário inserir o e-mail")
  @Email(message = "É necessário informar um e-mail válido")
  private String email;

  @NotBlank(message = "É necessário inserir a senha")
  @Size(min = 8, message = "A senha deverá conter no mínimo 8 caracteres")
  private String password;

  public CreateUserDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User toEntity() {
    return new User(getName(), getLastName(), getEmail(), getPassword());
  }
}
