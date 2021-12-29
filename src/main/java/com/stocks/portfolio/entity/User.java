package com.stocks.portfolio.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name="userdb")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String userName;
    private String userPass;
    private Double balance;

    public User(String userName, String userPass, Double balance) {
        this.userName = userName;
        this.userPass = userPass;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", balance=" + balance +
                '}';
    }
}
