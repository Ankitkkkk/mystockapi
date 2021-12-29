package com.stocks.portfolio.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stocks")
public class Stocks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String stockName;

    private Integer stockCount;

    private Integer stockPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stocks(String stockName, Integer stockCount, Integer stockPrice) {
        this.stockName = stockName;
        this.stockCount = stockCount;
        this.stockPrice = stockPrice;
    }

    public Stocks() {
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Integer stockPrice) {
        this.stockPrice = stockPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                ", stockCount=" + stockCount +
                ", stockPrice=" + stockPrice +
                '}';
    }
}
