package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.ResponseVO;

public interface VIPStrategyService {

    ResponseVO publishVIPStrategy(CardItemForm cardItemForm);

    ResponseVO getAllStrategy();

    ResponseVO deleteStrategyByID(int id);

    ResponseVO changeStrategy(int id,CardItemForm cardItemForm);
}
