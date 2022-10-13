package com.growtransparency.dtos;

import com.growtransparency.models.Organization;

public class ReturnCreatedOrganizationDTO {
  private String name;

  public ReturnCreatedOrganizationDTO(Organization organization) {
    this.name = organization.getName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
