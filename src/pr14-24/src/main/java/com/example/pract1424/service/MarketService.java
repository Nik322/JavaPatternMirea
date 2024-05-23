package com.example.pract1424.service;

import java.util.List;
import com.example.pract1424.dto.MarketDto;

public interface MarketService {

  List<MarketDto> getMarkets();

  void deleteMarket(long id);

  MarketDto createMarket(MarketDto marketDto);

  List<MarketDto> filterBy(String name);
}
