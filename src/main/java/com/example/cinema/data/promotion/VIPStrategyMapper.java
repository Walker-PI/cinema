package com.example.cinema.data.promotion;

import com.example.cinema.po.CardItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VIPStrategyMapper {

    int insertVIPStrategy(CardItem cardItem);

    List<CardItem>selectAllStrategy();

    void deleteStrategyByID(int id);

    void deleteAllStrategies();

    void updateStrategy(@Param("id")int id,@Param("target")double target,@Param("add")double add);
}
