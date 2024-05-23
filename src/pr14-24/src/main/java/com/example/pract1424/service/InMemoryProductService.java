package com.example.pract1424.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import com.example.pract1424.dto.ProductDto;
import com.example.pract1424.entity.Product;

@RequiredArgsConstructor
public class InMemoryProductService implements ProductService {

  private final List<Product> products = new ArrayList<>();

  @Override
  public ProductDto createProduct(ProductDto productDto) {
    products.add(new Product(productDto.getName(), productDto.getPrice()));
    return productDto;
  }

  @Override
  public void deleteProduct(long id) {
    products.remove((int) (id - 1));
  }

  @Override
  public List<ProductDto> getProducts() {
    return products.stream()
        .map(entity -> new ProductDto(entity.getName(), entity.getPrice(), 0))
        .collect(Collectors.toList());
  }

  @Override
  public List<ProductDto> filterByMarketId(long marketId) {
    return null;
  }
}
