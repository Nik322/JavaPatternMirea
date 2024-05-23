package com.example.pract1424.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.entity.Market;
import com.example.pract1424.mapper.MarketMapper;

@RequiredArgsConstructor
public class InMemoryMarketService implements MarketService {

  private final List<Market> markets = new ArrayList<>();

  public List<MarketDto> getMarkets() {
    return markets.stream().map(entity -> new MarketDto(entity.getName(), entity.getAddress(),
        Collections.EMPTY_LIST)).collect(Collectors.toList());
  }

  public void deleteMarket(long id) {
    markets.remove((int) id);
  }

  public MarketDto createMarket(MarketDto marketDto) {
    markets.add(new Market(marketDto.getName(), marketDto.getAddress()));
    return marketDto;
  }

  @Override
  public List<MarketDto> filterBy(String name) {
    return null;
  }
}
