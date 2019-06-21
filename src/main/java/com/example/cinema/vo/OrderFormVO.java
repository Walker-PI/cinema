package com.example.cinema.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderFormVO {
    private List<TicketVO>ticketVOList;//id要
    private int total;//定价
    private List<CouponVO>couponVOList;
    private String movieName;
    private String hallName;
    private double singlePrice;
    private String scheduleTime;
    private String generateTime;

}
