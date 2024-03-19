package org.rsvieira.inditextest.product.adapter.repository.database;

import org.rsvieira.inditextest.product.adapter.repository.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ProductPriceJpaRepository extends JpaRepository<ProductPrice, Long> {

  ProductPrice findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
      Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);

}
