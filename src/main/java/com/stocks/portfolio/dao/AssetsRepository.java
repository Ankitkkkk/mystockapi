package com.stocks.portfolio.dao;

import com.stocks.portfolio.entity.Assets;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetsRepository extends CrudRepository<Assets, Long> {
    public boolean existsBySid(Long sid);
    public List<Assets> findAllBySid(Long sid);
}
