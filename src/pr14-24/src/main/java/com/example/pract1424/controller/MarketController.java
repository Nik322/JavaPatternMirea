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
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.service.MarketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

  private final MarketService marketService;


  @GetMapping
  public List<MarketDto> getMarkets() {
    return marketService.getMarkets();
  }

  @DeleteMapping("/{id}")
  public void deleteMarket(@PathVariable long id) {
    marketService.deleteMarket(id);
  }

  @PutMapping
  public MarketDto createMarket(@RequestBody MarketDto marketDto) {
    return marketService.createMarket(marketDto);
  }

  @GetMapping("/filter-by/{field}")
  public List<MarketDto> filterBy(@PathVariable String field) {
    return marketService.filterBy(field);
  }
}
