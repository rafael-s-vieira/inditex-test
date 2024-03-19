package org.rsvieira.inditextest.product.adapter.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.rsvieira.inditextest.product.domain.ports.service.exception.InvalidParametersException;
import org.rsvieira.inditextest.product.domain.ports.service.exception.PriceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GlobalErrorResponse> handleInvalidRequest(MethodArgumentNotValidException e, WebRequest request) {
    Map<String, String> errors = new HashMap<>();

    var bindingResult = e.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    LOGGER.error("Received invalid request with parameters: {} and validation errors: {}", request.getParameterMap(), errors);

    var errorResponse = new GlobalErrorResponse("Invalid request", errors);

    return new ResponseEntity<>(errorResponse, BAD_REQUEST);
  }

  @ExceptionHandler(InvalidParametersException.class)
  public ResponseEntity<GlobalErrorResponse> handleInvalidParametersException(InvalidParametersException e) {
    LOGGER.error(e.getMessage());

    var errorResponse = new GlobalErrorResponse(e.getMessage(), null);

    return new ResponseEntity<>(errorResponse, BAD_REQUEST);
  }

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<GlobalErrorResponse> handlePriceNotFoundException(PriceNotFoundException e) {
    LOGGER.error(e.getMessage());

    var errorResponse = new GlobalErrorResponse(e.getMessage(), null);

    return new ResponseEntity<>(errorResponse, NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalErrorResponse> handlePriceNotFoundException(Exception e) {
    LOGGER.error("Unexpected exception", e);

    var errorResponse = new GlobalErrorResponse(e.getMessage(), null);

    return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
  }

}
