package com.example.cinema.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CouponVO {
    private int id;
    private String description;
    private String name;
    private double targetAmount;
    private double discountAmount;
    private Timestamp startTime;
    private Timestamp endTime;
}
