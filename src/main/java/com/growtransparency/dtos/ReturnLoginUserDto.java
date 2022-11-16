package com.growtransparency.dtos;

import java.util.List;

public class ReturnLoginUserDto {
  private String token;
  private boolean result;
  private List<String> roles;

  public ReturnLoginUserDto(String token, boolean result, List<String> roles) {
    this.token = token;
    this.result = result;
    this.roles = roles;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public List<String> getRoles() { return roles; }

  public void setRoles(List<String> roles) { this.roles = roles; }
}
