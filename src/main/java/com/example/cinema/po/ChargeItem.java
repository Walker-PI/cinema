package com.example.cinema.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChargeItem {
    int id;
    double amount;
    int user_id;
    Timestamp timestamp;
}
