package com.stocks.portfolio.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="admins")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="adminName", columnDefinition = "varchar(20)")
    private String adminName;
    @Column(name="adminPass", columnDefinition = "varchar(64)")
    private String adminPass;



    public Admin() {
    }

    public Admin(String adminName, String adminPass) {
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                '}';
    }
}
