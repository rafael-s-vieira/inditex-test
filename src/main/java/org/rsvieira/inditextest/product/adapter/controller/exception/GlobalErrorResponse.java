package org.rsvieira.inditextest.product.adapter.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GlobalErrorResponse {

  private String message;

  private Map<String, String> details;

}
