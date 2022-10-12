package com.growtransparency.dtos;

import com.growtransparency.models.Organization;

import java.util.List;

public class ReturnOrganizationsDTO {
  private String name;

  public ReturnOrganizationsDTO(Organization organization) {
    this.name = organization.getName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static List<ReturnOrganizationsDTO> convert(List<Organization> organizations) {
    return organizations.stream().map(ReturnOrganizationsDTO::new).toList();
  }
}
