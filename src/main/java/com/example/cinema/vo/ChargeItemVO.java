package com.example.cinema.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChargeItemVO {
    int id;
    double amount;
    Timestamp timestamp;
}
