package com.growtransparency.dtos;

import com.growtransparency.models.User;

public class ReturnCreatedUserDTO {
  private String name;
  private String email;

  public ReturnCreatedUserDTO(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
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
}
