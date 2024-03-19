package org.rsvieira.inditextest.product.adapter.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rsvieira.inditextest.product.adapter.controller.mapper.ProductPriceResponseMapper;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceRequest;
import org.rsvieira.inditextest.product.domain.ports.service.ProductPriceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.rsvieira.inditextest.product.Fixtures.DATE_TIME_PARAM;
import static org.rsvieira.inditextest.product.Fixtures.createDomainModelProductPrice;
import static org.rsvieira.inditextest.product.Fixtures.createFindProductPriceResponse;

@ExtendWith(MockitoExtension.class)
class ProductPriceControllerTest {

  @Mock
  private ProductPriceService productPriceService;
  @Mock
  private ProductPriceResponseMapper mapper;

  @InjectMocks
  private ProductPriceController productPriceController;

  @Test
  @SneakyThrows
  void givenValidInputWhenInvokedShouldReturnValidOutput() {
    var domainModelPrice = createDomainModelProductPrice();
    var responseModel = createFindProductPriceResponse();
    when(productPriceService.findProductPrice(1L, 2L, DATE_TIME_PARAM)).thenReturn(domainModelPrice);
    when(mapper.toResponse(domainModelPrice)).thenReturn(responseModel);

    var result = productPriceController.findProductPrice(new FindProductPriceRequest(1L, 2L, DATE_TIME_PARAM));

    assertThat(result.getBody()).usingRecursiveComparison().isEqualTo(responseModel);
    verify(productPriceService, times(1)).findProductPrice(1L, 2L, DATE_TIME_PARAM);
  }

}
