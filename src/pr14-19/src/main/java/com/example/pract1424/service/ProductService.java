package com.example.pract1424.service;

import java.util.List;
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.dto.ProductDto;

public interface ProductService {

  ProductDto createProduct(ProductDto productDto);

  void deleteProduct(long id);

  List<ProductDto> getProducts();

  List<ProductDto> filterByMarketId(long marketId);
}
