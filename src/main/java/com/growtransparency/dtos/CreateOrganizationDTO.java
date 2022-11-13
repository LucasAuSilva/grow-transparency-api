package com.growtransparency.dtos;

import com.growtransparency.models.Organization;

import javax.validation.constraints.NotBlank;

public record CreateOrganizationDTO(@NotBlank String name) {
  public Organization toEntity() {
    return new Organization(name);
  }
}
