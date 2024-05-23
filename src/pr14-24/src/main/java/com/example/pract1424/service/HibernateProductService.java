package com.example.pract1424.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import com.example.pract1424.dto.ProductDto;
import com.example.pract1424.entity.ProductEntity;
import com.example.pract1424.mapper.ProductMapper;

@RequiredArgsConstructor
public class HibernateProductService implements ProductService {

  private final SessionFactory sessionFactory;
  private final ProductMapper mapper;
  private Session session;

  @PostConstruct
  public void init() {
    session = sessionFactory.openSession();
  }

  @Override
  public ProductDto createProduct(ProductDto productDto) {
    var transaction = session.beginTransaction();
    session.persist(mapper.toDaoEntity(productDto));
    transaction.commit();
    return productDto;
  }

  @Override
  public void deleteProduct(long id) {
    var transaction = session.beginTransaction();
    session.remove(session.find(ProductEntity.class, id));
    transaction.commit();
  }

  @Override
  public List<ProductDto> getProducts() {
    var transaction = session.beginTransaction();
    var result = session.createQuery("select p from ProductEntity p", ProductEntity.class)
        .getResultList();
    transaction.commit();
    return result.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<ProductDto> filterByMarketId(long marketId) {
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
    Root<ProductEntity> root = query.from(ProductEntity.class);
    query.select(root).where(root.get("market_id").in(marketId));
    var result = session.createQuery(query).getResultList();
    return result.stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
