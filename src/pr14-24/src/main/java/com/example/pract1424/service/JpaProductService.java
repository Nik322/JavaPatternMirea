package com.example.pract1424.service;

import com.github.dockerjava.api.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.pract1424.dto.ProductDto;
import com.example.pract1424.entity.MarketEntity;
import com.example.pract1424.entity.ProductEntity;
import com.example.pract1424.mapper.ProductMapper;
import com.example.pract1424.repository.MarketRepository;
import com.example.pract1424.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaProductService implements ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final MarketRepository marketRepository;

  @Override
  public ProductDto createProduct(ProductDto productDto) {
    MarketEntity marketEntity = marketRepository.findById(productDto.getMarketId()).orElse(null);
    if (marketEntity == null) {
      throw new NotFoundException("No market entity for id %d".formatted(productDto.getMarketId()));
    }
    log.info("Creating product");
    ProductEntity productEntity = new ProductEntity();
    productEntity.setMarket(marketEntity);
    productEntity.setName(productDto.getName());
    productEntity.setPrice(productDto.getPrice());
    productRepository.save(productEntity);
    return productDto;
  }

  @Override
  public void deleteProduct(long id) {
    log.info("Deleting product");
    productRepository.deleteById(id);
  }

  @Override
  public List<ProductDto> getProducts() {
    log.info("Getting products");
    return productRepository.findAll().stream().map(productMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProductDto> filterByMarketId(long marketId) {
    log.info("Filtering products by market id");
    return productRepository.findAllByMarketId(marketId).stream().map(productMapper::toDto).collect(
        Collectors.toList());
  }
}
