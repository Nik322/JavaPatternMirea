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
import ru.gavrilovds.prac14.dto.MarketDto;
import ru.gavrilovds.prac14.entity.MarketEntity;
import ru.gavrilovds.prac14.mapper.MarketMapper;
import ru.gavrilovds.prac14.repository.MarketRepository;
import ru.gavrilovds.prac14.service.JpaMarketService;

@ExtendWith(MockitoExtension.class)
public class JpaMarketServiceTest {

  @Mock
  private MarketRepository marketRepository;

  @Mock
  private MarketMapper mapper;

  @InjectMocks
  private JpaMarketService marketService;

  private MarketDto marketDto;
  private MarketEntity marketEntity;

  @BeforeEach
  void setUp() {
    marketDto = new MarketDto("test", "test", Collections.emptyList());
    marketEntity = new MarketEntity();
  }

  @Test
  void testGetMarkets() {
    when(marketRepository.findAll()).thenReturn(Collections.singletonList(marketEntity));
    when(mapper.toDto(any(MarketEntity.class))).thenReturn(marketDto);

    List<MarketDto> result = marketService.getMarkets();

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(marketRepository, times(1)).findAll();
    verify(mapper, times(1)).toDto(marketEntity);
  }

  @Test
  void testDeleteMarket() {
    doNothing().when(marketRepository).deleteById(anyLong());

    marketService.deleteMarket(1L);

    verify(marketRepository, times(1)).deleteById(1L);
  }

  @Test
  void testCreateMarket() {
    when(mapper.toDaoEntity(any(MarketDto.class))).thenReturn(marketEntity);
    when(marketRepository.save(any(MarketEntity.class))).thenReturn(marketEntity);
    when(mapper.toDto(any(MarketEntity.class))).thenReturn(marketDto);

    MarketDto result = marketService.createMarket(marketDto);

    assertNotNull(result);
    verify(mapper, times(1)).toDaoEntity(marketDto);
    verify(marketRepository, times(1)).save(marketEntity);
  }

  @Test
  void testFilterBy() {
    when(marketRepository.findAll(any(Sort.class))).thenReturn(Collections.singletonList(marketEntity));
    when(mapper.toDto(any(MarketEntity.class))).thenReturn(marketDto);

    List<MarketDto> result = marketService.filterBy("name");

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(marketRepository, times(1)).findAll(Sort.by("name"));
    verify(mapper, times(1)).toDto(marketEntity);
  }
}
