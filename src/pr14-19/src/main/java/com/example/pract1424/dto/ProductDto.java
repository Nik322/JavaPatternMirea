package com.example.pract1424.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductDto {

  private final String name;
  private final int price;
  private final long marketId;
}
