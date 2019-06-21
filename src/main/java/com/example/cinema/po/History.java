package com.example.cinema.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class History {
    int id;

    /**
     * 支付类型
     */
    int payType;

    /**
     * 支付完成时间
     */
    Timestamp time;

    /**
     * 消费金额
     */
    double fare;

    /**
     * 消费种类
     */
    String type;

    /**
     * 用户id
     */
    int userId;

    public History(){}

    public History(int payType,Timestamp timestamp,double fare,String type,int userId){
        this.payType=payType;
        this.time=timestamp;
        this.fare=fare;
        this.type=type;
        this.userId = userId;
    }


}
