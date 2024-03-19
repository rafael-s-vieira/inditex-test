package org.rsvieira.inditextest.product.domain.ports.data;

import org.rsvieira.inditextest.product.domain.model.ProductPrice;

import java.time.LocalDateTime;

public interface ProductPriceRepository {

  ProductPrice findProductPrice(Long brandId, Long productId, LocalDateTime date);
}
