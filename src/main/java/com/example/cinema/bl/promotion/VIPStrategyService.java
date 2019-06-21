package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface VIPStrategyService {

    ResponseVO publishVIPStrategy(List<CardItemForm> cardItemFormList);

    ResponseVO getAllStrategy();

    ResponseVO deleteStrategyByID(int id);

    ResponseVO changeStrategy(int id,CardItemForm cardItemForm);
}
