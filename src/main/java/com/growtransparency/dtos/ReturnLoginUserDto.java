package com.growtransparency.dtos;

public class ReturnLoginUserDto {
  private String token;
  private boolean result;

  public ReturnLoginUserDto(String token, boolean result) {
    this.token = token;
    this.result = result;
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
}
