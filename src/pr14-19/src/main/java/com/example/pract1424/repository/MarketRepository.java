package com.example.pract1424.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pract1424.entity.MarketEntity;

@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, Long> {

}
