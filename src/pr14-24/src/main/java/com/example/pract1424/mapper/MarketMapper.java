package com.example.pract1424.mapper;

import org.mapstruct.Mapper;
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.entity.MarketEntity;

@Mapper(componentModel = "spring")
public interface MarketMapper {

  MarketEntity toDaoEntity(MarketDto marketDto);

  MarketDto toDto(MarketEntity marketEntity);
}
