package com.stocks.portfolio.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "assets")
public class Assets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long sid;
    private String stockName;
    private Double average;
    private Long stockCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assets(Long sid, String stockName, Double average, Long stockCount) {
        this.sid = sid;
        this.stockName = stockName;
        this.average = average;
        this.stockCount = stockCount;
    }
}
