package com.example.cinema.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChargeItem {
    int id;

    /**
     * 充值金额
     */
    double amount;

    /**
     * 用户id
     */
    int user_id;

    /**
     * 充值时间
     */
    Timestamp timestamp;
}
