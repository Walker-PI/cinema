package com.example.cinema.vo;

import com.example.cinema.po.History;
import lombok.Data;

import java.util.Date;

@Data
public class HistoryVO {
    int id;
    int payType;//0表示阿里，1表示会员卡
    Date time;
    double fare;
    String type;//"ticket"表示买票的,"charge"表示充钱的,"card"表示买卡的,"upCard"表示升级卡的

    public HistoryVO(){}

    public HistoryVO(History history){
        this.id = history.getId();
        this.payType = history.getPayType();
        this.time = history.getTime();
        this.fare = history.getFare();
        this.type = history.getType();
    }

}

