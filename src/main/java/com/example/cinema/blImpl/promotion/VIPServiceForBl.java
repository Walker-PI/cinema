package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;

public interface VIPServiceForBl {
    public ResponseVO getCardByUserId(int userId);
    public ResponseVO updateBalance(int id,double balance);

    VIPCard getVIPCardByUserId(int userId);

    VIPCard addCard(int userId, int card_type);
}
