package com.example.cinema.po;
import lombok.Data;

@Data
public class Order {
    int id;

    /**
     * 订单名
     */
    String name;

    /**
     * 订单内容
     */
    String content;

    /**
     * 订单价格
     */
    double price;

    /**
     * 订单状态
     */
    int status;

    public Order(){}
    public Order(String name,String content,double price,int status){
        this.name=name;
        this.content=content;
        this.price=price;
        this.status=status;
    }
}
