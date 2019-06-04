package com.example.cinema.po;
import lombok.Data;

@Data
public class Order {
    int id;
    String name;
    String content;
    double price;
    int status;

    public Order(){}
    public Order(String name,String content,double price,int status){
        this.name=name;
        this.content=content;
        this.price=price;
        this.status=status;
    }
}
