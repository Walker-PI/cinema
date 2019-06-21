package com.example.cinema.po;


import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/14.
 */

public class VIPCard {

    public static final double price = 25;

    public static final String description="";

    /**
     * 用户id
     */
    private int userId;

    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡余额
     */
    private double balance;

    /**
     * 办卡日期
     */
    private Timestamp joinDate;

    /**
     * vip卡的类型 0：普通卡（没有充值优惠）  1 会员卡（有充值优惠）
     */
    private int vipType;


    public VIPCard() {

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public int getVipType() {
        return vipType;
    }
}
