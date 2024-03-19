package org.rsvieira.inditextest.product.adapter.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rsvieira.inditextest.product.domain.model.ProductPrice;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

  @Mapping(target = "brandId", source = "brand.id")
  ProductPrice toDomain(org.rsvieira.inditextest.product.adapter.repository.model.ProductPrice source);

}
