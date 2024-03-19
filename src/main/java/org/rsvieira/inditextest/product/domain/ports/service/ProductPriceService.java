package org.rsvieira.inditextest.product.domain.ports.service;

import org.rsvieira.inditextest.product.domain.model.ProductPrice;
import org.rsvieira.inditextest.product.domain.ports.service.exception.InvalidParametersException;
import org.rsvieira.inditextest.product.domain.ports.service.exception.PriceNotFoundException;

import java.time.LocalDateTime;

public interface ProductPriceService {

  ProductPrice findProductPrice(Long brandId, Long productId, LocalDateTime date) throws InvalidParametersException, PriceNotFoundException;
}
