package ru.gavrilovds.prac14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ru.gavrilovds.prac14.dto.ProductDto;
import ru.gavrilovds.prac14.entity.MarketEntity;
import ru.gavrilovds.prac14.entity.ProductEntity;
import ru.gavrilovds.prac14.mapper.ProductMapper;
import ru.gavrilovds.prac14.repository.MarketRepository;
import ru.gavrilovds.prac14.repository.ProductRepository;
import ru.gavrilovds.prac14.service.JpaProductService;

@ExtendWith(MockitoExtension.class)
public class JpaProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private MarketRepository marketRepository;

  @Mock
  private ProductMapper productMapper;

  @InjectMocks
  private JpaProductService productService;

  private ProductDto productDto;
  private ProductEntity productEntity;
  private MarketEntity marketEntity;

  @BeforeEach
  void setUp() {
    productDto = new ProductDto();
    productDto.setMarketId(1L);
    productDto.setName("Test Product");
    productDto.setPrice(100);

    productEntity = new ProductEntity();
    productEntity.setName("Test Product");
    productEntity.setPrice(100);

    marketEntity = new MarketEntity();
    marketEntity.setId(1L);
  }

  @Test
  void testCreateProduct() {
    when(marketRepository.findById(productDto.getMarketId())).thenReturn(Optional.of(marketEntity));
    when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

    ProductDto result = productService.createProduct(productDto);

    assertNotNull(result);
    assertEquals(productDto.getName(), result.getName());
    assertEquals(productDto.getPrice(), result.getPrice());
    verify(marketRepository, times(1)).findById(productDto.getMarketId());
    verify(productRepository, times(1)).save(any(ProductEntity.class));
  }

  @Test
  void testCreateProductMarketNotFound() {
    when(marketRepository.findById(productDto.getMarketId())).thenReturn(Optional.empty());

    NotFoundException exception = assertThrows(NotFoundException.class, () -> {
      productService.createProduct(productDto);
    });

    assertEquals("No market entity for id 1", exception.getMessage());
    verify(marketRepository, times(1)).findById(productDto.getMarketId());
    verify(productRepository, times(0)).save(any(ProductEntity.class));
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productRepository).deleteById(anyLong());

    productService.deleteProduct(1L);

    verify(productRepository, times(1)).deleteById(1L);
  }

  @Test
  void testGetProducts() {
    when(productRepository.findAll()).thenReturn(Collections.singletonList(productEntity));
    when(productMapper.toDto(any(ProductEntity.class))).thenReturn(productDto);

    List<ProductDto> result = productService.getProducts();

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(productRepository, times(1)).findAll();
    verify(productMapper, times(1)).toDto(productEntity);
  }

  @Test
  void testFilterByMarketId() {
    when(productRepository.findAllByMarketId(1L)).thenReturn(Collections.singletonList(productEntity));
    when(productMapper.toDto(any(ProductEntity.class))).thenReturn(productDto);

    List<ProductDto> result = productService.filterByMarketId(1L);

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(productRepository, times(1)).findAllByMarketId(1L);
    verify(productMapper, times(1)).toDto(productEntity);
  }
}