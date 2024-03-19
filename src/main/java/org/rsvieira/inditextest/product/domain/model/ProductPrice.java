package org.rsvieira.inditextest.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {

  private Long id;

  private Long brandId;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Long priceList;

  private Long productId;

  private Long priority;

  private BigDecimal price;

  private String currency;

}
