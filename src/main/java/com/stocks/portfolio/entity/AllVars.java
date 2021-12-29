package com.stocks.portfolio.entity;

import java.io.Serializable;

public class AllVars implements Serializable {
    private String adminName;
    private String adminPass;
    private String newPass;
    private String userName;
    private  String userPass;
    private  String sName;
    private  Integer sCount;
    private  Integer sPrice;
    private  Integer balance;
    private String message;

    public AllVars(String adminName, String adminPass, String newPass, String userName, String userPass, String sName, Integer sCount, Integer sPrice, Integer balance, String message) {
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.newPass = newPass;
        this.userName = userName;
        this.userPass = userPass;
        this.sName = sName;
        this.sCount = sCount;
        this.sPrice = sPrice;
        this.balance = balance;
        this.message = message;
    }

    public AllVars() {
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

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsCount() {
        return sCount;
    }

    public void setsCount(Integer sCount) {
        this.sCount = sCount;
    }

    public Integer getsPrice() {
        return sPrice;
    }

    public void setsPrice(Integer sPrice) {
        this.sPrice = sPrice;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AllVars{" +
                "adminName='" + adminName + '\'' +
                ", adminPass='" + adminPass + '\'' +
                ", newPass='" + newPass + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", sName='" + sName + '\'' +
                ", sCount=" + sCount +
                ", sPrice=" + sPrice +
                ", balance=" + balance +
                ", message='" + message + '\'' +
                '}';
    }
}
