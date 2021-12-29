package com.stocks.portfolio.dao;

import com.stocks.portfolio.entity.Admin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Admins extends CrudRepository<Admin, Long> {
    Admin findByAdminName(String name);

    boolean existsByAdminName(String name);

}
