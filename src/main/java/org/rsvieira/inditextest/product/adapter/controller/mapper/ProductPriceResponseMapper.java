package org.rsvieira.inditextest.product.adapter.controller.mapper;

import org.mapstruct.Mapper;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceResponse;
import org.rsvieira.inditextest.product.domain.model.ProductPrice;

@Mapper(componentModel = "spring")
public interface ProductPriceResponseMapper {

  FindProductPriceResponse toResponse(ProductPrice source);

}
