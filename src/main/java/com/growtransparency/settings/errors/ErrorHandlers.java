package com.growtransparency.settings.errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandlers {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public List<ResponseErrorValidation> handle(MethodArgumentNotValidException exception) {
    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    return fieldErrors.stream().map(ResponseErrorValidation::new).toList();
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = DataIntegrityViolationException.class)
  public ResponseError handle(DataIntegrityViolationException exception) {

    return new ResponseError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
  }
}
