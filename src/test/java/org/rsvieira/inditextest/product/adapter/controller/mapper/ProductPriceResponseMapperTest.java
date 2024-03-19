package org.rsvieira.inditextest.product.adapter.controller.mapper;

import org.junit.jupiter.api.Test;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceResponse;
import org.rsvieira.inditextest.product.domain.model.ProductPrice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rsvieira.inditextest.product.Fixtures.createDomainModelProductPrice;
import static org.rsvieira.inditextest.product.Fixtures.createFindProductPriceResponse;

class ProductPriceResponseMapperTest {

  private final ProductPriceResponseMapper productPriceResponseMapper = new ProductPriceResponseMapperImpl();

  @Test
  void givenValidSourceWhenInvokedShouldMapToValidDestination() {
    var source = createDomainModelProductPrice();
    var expectedResult = createFindProductPriceResponse();

    var result = productPriceResponseMapper.toResponse(source);

    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
  }

  @Test
  void givenEmptySourceWhenInvokedShouldMapToEmptyDestination() {
    var source = new ProductPrice();
    var expectedResult = new FindProductPriceResponse();

    var result = productPriceResponseMapper.toResponse(source);

    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
  }
}