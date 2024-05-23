package com.example.pract1424.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.mapper.MarketMapper;
import com.example.pract1424.repository.MarketRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class JpaMarketService implements MarketService {

  private final MarketRepository marketRepository;
  private final MarketMapper mapper;

  @Override
  public List<MarketDto> getMarkets() {
    log.info("Getting markets");
    return marketRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public void deleteMarket(long id) {
    log.info("Deleting market");
    marketRepository.deleteById(id);
  }

  @Override
  public MarketDto createMarket(MarketDto marketDto) {
    log.info("Creating market");
    marketRepository.save(mapper.toDaoEntity(marketDto));
    return marketDto;
  }

  @Override
  public List<MarketDto> filterBy(String field) {
    log.info("Filtering markets by %s".formatted(field));
    return marketRepository.findAll(Sort.by(field)).stream().map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
