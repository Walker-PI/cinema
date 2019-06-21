package com.example.cinema.data.promotion;

import com.example.cinema.po.CardItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VIPStrategyMapperTest {

    @Autowired
    VIPStrategyMapper vipStrategyMapper;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertVIPStrategy() {
        CardItem cardItem = new CardItem();
        cardItem.setTarget(100);
        cardItem.setAdd(30);
        vipStrategyMapper.insertVIPStrategy(cardItem);
    }

    @Test
    public void selectAllStrategy() {
        List<CardItem>cardItems = vipStrategyMapper.selectAllStrategy();
        System.out.println(cardItems.size());
    }

    @Test
    public void deleteStrategyByID() {
        vipStrategyMapper.deleteStrategyByID(1);
    }

    @Test
    public void updateStrategy() {
        vipStrategyMapper.updateStrategy(2,90,20);
    }
}