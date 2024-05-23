package com.example.pract1424.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.pract1424.dto.ProductDto;
import com.example.pract1424.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductEntity toDaoEntity(ProductDto productDto);

  @Mapping(target = "marketId", source = "productEntity.market.id")
  ProductDto toDto(ProductEntity productEntity);
}
