package org.rsvieira.inditextest.product.domain.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rsvieira.inditextest.product.domain.ports.data.ProductPriceRepository;
import org.rsvieira.inditextest.product.domain.ports.service.exception.InvalidParametersException;
import org.rsvieira.inditextest.product.domain.ports.service.exception.PriceNotFoundException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.rsvieira.inditextest.product.Fixtures.DATE_TIME_PARAM;
import static org.rsvieira.inditextest.product.Fixtures.createDomainModelProductPrice;

@ExtendWith(MockitoExtension.class)
class ProductPriceServiceImplTest {

  @Mock
  private ProductPriceRepository productPriceRepository;

  @InjectMocks
  private ProductPriceServiceImpl productPriceService;

  @Test
  @SneakyThrows
  void givenValidInputAndExistingPriceWhenServiceIsInvokedShouldReturnPrice() {
    var productPrice = createDomainModelProductPrice();
    when(productPriceRepository
        .findProductPrice(1L, 2L, DATE_TIME_PARAM))
        .thenReturn(productPrice);

    var result = productPriceService.findProductPrice(1L, 2L, DATE_TIME_PARAM);

    assertThat(result).usingRecursiveComparison().isEqualTo(productPrice);
  }

  @ParameterizedTest(name = "{index}: {3}")
  @MethodSource("invalidInputData")
  void givenInvalidInputWhenServiceInInvokedShouldThrowInvalidParametersException(Long brandId, Long productId, LocalDateTime date, String scenario) {
    assertThrows(InvalidParametersException.class, () -> productPriceService.findProductPrice(brandId, productId, date));
  }

  @Test
  void givenValidInputButNullPriceWhenServiceIsInvokedShouldReturnPriceNotFoundException() {
    when(productPriceRepository
        .findProductPrice(1L, 2L, DATE_TIME_PARAM))
        .thenReturn(null);

    assertThrows(PriceNotFoundException.class, () -> productPriceService.findProductPrice(1L, 2L, DATE_TIME_PARAM));
  }

  private static Stream<Arguments> invalidInputData() {
    return Stream.of(
        Arguments.of(null, 2L, DATE_TIME_PARAM, "Null brandId"),
        Arguments.of(0L, 2L, DATE_TIME_PARAM, "Invalid brandId"),
        Arguments.of(1L, null, DATE_TIME_PARAM, "Null productId"),
        Arguments.of(1L, 0L, DATE_TIME_PARAM, "Invalid productId"),
        Arguments.of(1L, 2L, null, "Null date"),
        Arguments.of(null, null, null, "Null all params")
    );
  }

}