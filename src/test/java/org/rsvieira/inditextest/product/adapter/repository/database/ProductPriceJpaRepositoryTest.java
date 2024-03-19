package org.rsvieira.inditextest.product.adapter.repository.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = "classpath:data/test-data.sql")
@ActiveProfiles("test")
class ProductPriceJpaRepositoryTest {

  @Autowired
  private ProductPriceJpaRepository productPriceRepository;

  @Test
  void givenValidInputWhenInvokedShouldReturnCorrectRecord() {
    var date = LocalDateTime.of(2020, 6, 14, 16, 0);

    var result =
        productPriceRepository
            .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1L,
                35455L, date, date);

    assertThat(result).isNotNull();
    assertThat(result.getPriceList()).isEqualTo(2L);
  }

  @Test
  void givenOutOfLimitsInputWhenInvokedShouldReturnNull() {
    var date = LocalDateTime.of(2023, 6, 14, 16, 0);

    var result =
        productPriceRepository
            .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1L,
                35455L, date, date);

    assertThat(result).isNull();
  }
}
