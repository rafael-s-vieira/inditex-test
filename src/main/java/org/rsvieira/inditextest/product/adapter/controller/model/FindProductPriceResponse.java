package org.rsvieira.inditextest.product.adapter.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindProductPriceResponse {

  private Long brandId;

  private Long productId;

  private Long priceList;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private BigDecimal price;

  private String currency;

}
