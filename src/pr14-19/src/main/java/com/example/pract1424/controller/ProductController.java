package com.example.pract1424.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pract1424.dto.ProductDto;
import com.example.pract1424.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;


  @GetMapping
  public List<ProductDto> getProducts() {
    return productService.getProducts();
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
  }

  @PutMapping
  public ProductDto createProduct(@RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }

  @GetMapping("filter-by-market-id/{marketId}")
  public List<ProductDto> filterByMarketId(@PathVariable long marketId) {
    return productService.filterByMarketId(marketId);
  }
}
