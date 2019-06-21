package com.example.cinema.po;

import com.example.cinema.vo.CardItemForm;
import lombok.Data;
@Data
public class CardItem {
    int id;

    /**
     * 门槛金额
     */
    double target;

    /**
     * 优惠金额
     */

    double add;
    public CardItem(){}
    public CardItem(CardItemForm cardItemForm){
        this.target = cardItemForm.getTarget();
        this.add = cardItemForm.getAdd();
    }
}
