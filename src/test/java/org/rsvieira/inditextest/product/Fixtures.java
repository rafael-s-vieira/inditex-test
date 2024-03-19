package org.rsvieira.inditextest.product;

import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceResponse;
import org.rsvieira.inditextest.product.adapter.repository.model.Brand;
import org.rsvieira.inditextest.product.adapter.repository.model.ProductPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Fixtures {

  public static final LocalDateTime DATE_TIME_PARAM = LocalDateTime.of(2020, 6, 14, 10, 0);

  public static final FindProductPriceResponse EXPECTED_RESPONSE_1 = new FindProductPriceResponse(
      1L, 35455L, 1L, LocalDateTime.of(2020, 6, 14, 0, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("35.50"), "EUR");
  public static final FindProductPriceResponse EXPECTED_RESPONSE_2 = new FindProductPriceResponse(
      1L, 35455L, 2L, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30, 0), new BigDecimal("25.45"), "EUR");
  public static final FindProductPriceResponse EXPECTED_RESPONSE_3 = new FindProductPriceResponse(
      1L, 35455L, 3L, LocalDateTime.of(2020, 6, 15, 0, 0), LocalDateTime.of(2020, 6, 15, 11, 0, 0), new BigDecimal("30.50"), "EUR");
  public static final FindProductPriceResponse EXPECTED_RESPONSE_4 = new FindProductPriceResponse(
      1L, 35455L, 4L, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("38.95"), "EUR");



  public static ProductPrice createDatabaseModelProductPrice() {
    return ProductPrice.builder()
        .id(1L)
        .brand(Brand.builder().id(1L).name("brand").build())
        .startDate(LocalDateTime.of(2020, 1, 1, 0, 0))
        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
        .priceList(1L)
        .productId(2L)
        .priority(3L)
        .price(BigDecimal.TEN)
        .currency("EUR")
        .build();
  }

  public static org.rsvieira.inditextest.product.domain.model.ProductPrice createDomainModelProductPrice() {
    return new org.rsvieira.inditextest.product.domain.model.ProductPrice(
        1L,
        1L,
        LocalDateTime.of(2020, 1, 1, 0, 0),
        LocalDateTime.of(2020, 12, 31, 23, 59),
        1L,
        2L,
        3L,
        BigDecimal.TEN,
        "EUR");
  }

  public static FindProductPriceResponse createFindProductPriceResponse() {
    return new FindProductPriceResponse(
        1L,
        2L,
        1L,
        LocalDateTime.of(2020, 1, 1, 0, 0),
        LocalDateTime.of(2020, 12, 31, 23, 59),
        BigDecimal.TEN,
        "EUR");
  }
}
