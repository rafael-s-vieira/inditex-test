package org.rsvieira.inditextest.product.adapter.repository.mapper;

import org.junit.jupiter.api.Test;
import org.rsvieira.inditextest.product.adapter.repository.model.ProductPrice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rsvieira.inditextest.product.Fixtures.createDatabaseModelProductPrice;
import static org.rsvieira.inditextest.product.Fixtures.createDomainModelProductPrice;

class ProductPriceMapperTest {

  private final ProductPriceMapper productPriceMapper = new ProductPriceMapperImpl();

  @Test
  void givenValidSourceWhenInvokedShouldMapToValidDestination() {
    var source = createDatabaseModelProductPrice();
    var expectedResult = createDomainModelProductPrice();

    var result = productPriceMapper.toDomain(source);

    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
  }

  @Test
  void givenEmptySourceWhenInvokedShouldMapToEmptyDestination() {
    var source = new ProductPrice();
    var expectedResult = new org.rsvieira.inditextest.product.domain.model.ProductPrice();

    var result = productPriceMapper.toDomain(source);

    assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
  }

}