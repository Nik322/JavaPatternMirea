package com.example.pract1424.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Container;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import com.example.pract1424.dto.MarketDto;
import com.example.pract1424.entity.MarketEntity;
import com.example.pract1424.mapper.MarketMapper;

@RequiredArgsConstructor
public class HibernateMarketService implements MarketService {

  private final SessionFactory sessionFactory;
  private final MarketMapper mapper;

  private Session session;


  @PostConstruct
  public void init() {
    session = sessionFactory.openSession();
  }

  @Override
  public List<MarketDto> getMarkets() {
    var transaction = session.beginTransaction();
    var result = session.createQuery("select m  from MarketEntity m ", MarketEntity.class)
        .getResultList();
    transaction.commit();
    return result.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public void deleteMarket(long id) {
    var transaction = session.beginTransaction();
    session.remove(session.find(MarketEntity.class, id));
    transaction.commit();
  }

  @Override
  public MarketDto createMarket(MarketDto marketDto) {
    var entity = mapper.toDaoEntity(marketDto);
    var transaction = session.beginTransaction();
    session.persist(entity);
    transaction.commit();
    return marketDto;
  }

  @Override
  public List<MarketDto> filterBy(String field) {
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<MarketEntity> query = builder.createQuery(MarketEntity.class);
    Root<MarketEntity> root = query.from(MarketEntity.class);
    query.select(root).orderBy(builder.asc(root.get(field)));
    var result = session.createQuery(query).getResultList();
    return result.stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
