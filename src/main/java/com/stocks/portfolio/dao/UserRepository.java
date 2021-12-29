package com.stocks.portfolio.dao;

import com.stocks.portfolio.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUserName(String uname);
    User findByUserName(String uname);
    List<User> findAll();
}
