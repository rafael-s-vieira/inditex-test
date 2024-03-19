package org.rsvieira.inditextest.product.adapter.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rsvieira.inditextest.product.adapter.repository.database.ProductPriceJpaRepository;
import org.rsvieira.inditextest.product.adapter.repository.mapper.ProductPriceMapper;
import org.rsvieira.inditextest.product.domain.model.ProductPrice;
import org.rsvieira.inditextest.product.domain.ports.data.ProductPriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Repository
@RequiredArgsConstructor
public class ProductPriceRepositoryImpl implements ProductPriceRepository {

  private final ProductPriceJpaRepository repository;

  private final ProductPriceMapper mapper;

  @Override
  public ProductPrice findProductPrice(Long brandId, Long productId, LocalDateTime date) {
    LOGGER.info("Repository finding product price for brandId: {}, productId: {}, date: {}", brandId, productId, date);

    return Optional.ofNullable(repository
        .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, date, date))
        .map(mapper::toDomain)
        .orElseGet(() -> {
          LOGGER.warn("Price not found for brandId: {}, productId: {}, date: {}", brandId, productId, date);
          return null;
        });
  }
}
