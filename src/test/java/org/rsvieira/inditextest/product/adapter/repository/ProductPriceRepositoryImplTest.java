package org.rsvieira.inditextest.product.adapter.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rsvieira.inditextest.product.adapter.repository.database.ProductPriceJpaRepository;
import org.rsvieira.inditextest.product.adapter.repository.mapper.ProductPriceMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.rsvieira.inditextest.product.Fixtures.DATE_TIME_PARAM;
import static org.rsvieira.inditextest.product.Fixtures.createDatabaseModelProductPrice;
import static org.rsvieira.inditextest.product.Fixtures.createDomainModelProductPrice;

@ExtendWith(MockitoExtension.class)
class ProductPriceRepositoryImplTest {

  @Mock
  private ProductPriceJpaRepository productPriceRepository;
  @Mock
  private ProductPriceMapper productPriceMapper;

  @InjectMocks
  private ProductPriceRepositoryImpl productPriceRepositoryImpl;

  @Test
  void givenValidInputWhenInvokedShouldReturnValidDomainOutput() {
    var databaseModelPrice = createDatabaseModelProductPrice();
    var domainModelPrice = createDomainModelProductPrice();
    when(productPriceRepository
        .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1L, 2L,
            DATE_TIME_PARAM, DATE_TIME_PARAM))
        .thenReturn(databaseModelPrice);
    when(productPriceMapper.toDomain(databaseModelPrice)).thenReturn(domainModelPrice);

    var result = productPriceRepositoryImpl.findProductPrice(1L, 2L, DATE_TIME_PARAM);

    assertThat(result).usingRecursiveComparison().isEqualTo(domainModelPrice);
    verify(productPriceMapper, times(1)).toDomain(databaseModelPrice);
  }

  @Test
  void givenOutOfLimitsInputWhenInvokedShouldReturnNull() {
    when(productPriceRepository
        .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1L, 2L,
            DATE_TIME_PARAM, DATE_TIME_PARAM))
        .thenReturn(null);

    var result = productPriceRepositoryImpl.findProductPrice(1L, 2L, DATE_TIME_PARAM);

    assertThat(result).isNull();
    verify(productPriceMapper, never()).toDomain(any());
  }
}