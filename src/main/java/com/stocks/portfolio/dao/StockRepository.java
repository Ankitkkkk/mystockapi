package com.stocks.portfolio.dao;

import com.stocks.portfolio.entity.Stocks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stocks, Long>{
    boolean existsByStockName(String stock);
    Stocks findByStockName(String stock);
    List<Stocks> findAll();
}
