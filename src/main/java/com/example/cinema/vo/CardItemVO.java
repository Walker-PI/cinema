package com.example.cinema.vo;

import com.example.cinema.po.CardItem;
import lombok.Data;
@Data
public class CardItemVO {
    int id;
    double target;
    double add;
    public CardItemVO(CardItem cardItem){
        this.id = cardItem.getId();
        this.target = cardItem.getTarget();
        this.add = cardItem.getAdd();
    }
}
