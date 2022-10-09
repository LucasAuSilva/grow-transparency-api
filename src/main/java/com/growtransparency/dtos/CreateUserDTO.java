package com.growtransparency.dtos;

import com.growtransparency.models.User;

public class CreateUserDTO {
  private String name;
  private String email;
  private String password;

  public CreateUserDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    return new User(getName(), getEmail(), getPassword());
  }
}
