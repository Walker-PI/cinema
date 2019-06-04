package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.po.CardItem;
import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.ResponseVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class VIPStrategyServiceImplTest {

    @Autowired
    VIPStrategyService vipStrategyService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void publishVIPStrategy() {
        CardItemForm cardItemForm = new CardItemForm();
        cardItemForm.setTarget(200);
        cardItemForm.setAdd(100);
        vipStrategyService.publishVIPStrategy(cardItemForm);
    }

    @Test
    public void getAllStrategy() {
         ResponseVO responseVO=vipStrategyService.getAllStrategy();
         System.out.println(responseVO.getSuccess());
    }

    @Test
    public void deleteStrategyByID() {
        vipStrategyService.deleteStrategyByID(2);
    }

    @Test
    public void changeStrategy() {
        CardItemForm cardItemForm = new CardItemForm();
        cardItemForm.setTarget(300);
        cardItemForm.setAdd(200);
        vipStrategyService.changeStrategy(3,cardItemForm);
    }

    @Test
    public void cardItem2CardItemVO() {
    }
}