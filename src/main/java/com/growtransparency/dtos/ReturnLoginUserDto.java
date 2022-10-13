package com.growtransparency.dtos;

public class ReturnLoginUserDto {
  private boolean result;

  public ReturnLoginUserDto(boolean result) {
    this.result = result;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }
}
