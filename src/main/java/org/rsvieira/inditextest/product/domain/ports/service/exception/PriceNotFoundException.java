package org.rsvieira.inditextest.product.domain.ports.service.exception;

public class PriceNotFoundException extends Exception {

  public PriceNotFoundException(String message) {
    super(message);
  }
}
