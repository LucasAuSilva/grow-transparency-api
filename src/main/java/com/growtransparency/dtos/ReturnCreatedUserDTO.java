package com.growtransparency.dtos;

import com.growtransparency.models.User;

public class ReturnCreatedUserDTO {
  private String name;

  private String lastName;
  
  private String email;

  public ReturnCreatedUserDTO(User user) {
    this.name = user.getName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
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
}
