package com.growtransparency.settings.errors;

import org.springframework.validation.FieldError;

public class ResponseErrorValidation {
  private String fieldName;
  private Integer status;
  private String message;

  public ResponseErrorValidation(String fieldName, Integer status, String message) {
    this.fieldName = fieldName;
    this.status = status;
    this.message = message;
  }

  public ResponseErrorValidation(FieldError error) {
    this.fieldName = error.getField();
    this.status = 400;
    this.message = error.getDefaultMessage();
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
