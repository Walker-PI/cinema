package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;

public interface VIPServiceForBl {
    ResponseVO getCardByUserId(int userId);
    ResponseVO updateBalance(int id,double balance);

    VIPCard getVIPCardByUserId(int userId);

    VIPCard addCard(int userId, int card_type);
}
