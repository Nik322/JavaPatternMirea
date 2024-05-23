package com.example.pract1424.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MarketDto {

  private final String name;
  private final String address;
  private final List<ProductDto> products;
}
