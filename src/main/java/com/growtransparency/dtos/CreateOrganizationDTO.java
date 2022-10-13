package com.growtransparency.dtos;

import com.growtransparency.models.Organization;

import javax.validation.constraints.NotBlank;

public class CreateOrganizationDTO {
  @NotBlank(message = "É necessário inserir o nome da organização")
  private String name;

  public CreateOrganizationDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Organization toEntity() {
    return new Organization(getName());
  }
}
