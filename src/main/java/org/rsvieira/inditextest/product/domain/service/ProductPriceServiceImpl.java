package org.rsvieira.inditextest.product.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rsvieira.inditextest.product.domain.model.ProductPrice;
import org.rsvieira.inditextest.product.domain.ports.data.ProductPriceRepository;
import org.rsvieira.inditextest.product.domain.ports.service.ProductPriceService;
import org.rsvieira.inditextest.product.domain.ports.service.exception.InvalidParametersException;
import org.rsvieira.inditextest.product.domain.ports.service.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Log4j2
@Service
@AllArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

  private final ProductPriceRepository productPriceRepository;

  @Override
  public ProductPrice findProductPrice(Long brandId, Long productId, LocalDateTime date) throws InvalidParametersException, PriceNotFoundException {
    LOGGER.info("Service finding product price for brandId: {}, productId: {}, date: {}", brandId, productId, date);

    if (!validateInput(brandId, productId, date)) {
      throw new InvalidParametersException(MessageFormat.format("Invalid parameters for brandId: {0}, productId: {1}, date: {2}", brandId, productId, date));
    }

    var productPrice = productPriceRepository.findProductPrice(brandId, productId, date);

    if (productPrice == null) {
      throw new PriceNotFoundException(MessageFormat.format("Price not found for brandId: {0}, productId: {1}, date: {2}", brandId, productId, date));
    }

    return productPrice;
  }

  private boolean validateInput(Long brandId, Long productId, LocalDateTime date) {
    return brandId != null && brandId > 0 && productId != null && productId > 0 && date != null;
  }
}
