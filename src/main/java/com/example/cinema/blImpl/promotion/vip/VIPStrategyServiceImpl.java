package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.blImpl.promotion.vip.VIPStrategyServiceForBl;
import com.example.cinema.data.promotion.VIPStrategyMapper;
import com.example.cinema.po.CardItem;
import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.CardItemVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VIPStrategyServiceImpl implements VIPStrategyService, VIPStrategyServiceForBl {

    @Autowired
    VIPStrategyMapper vipStrategyMapper;

    @Override
    public ResponseVO publishVIPStrategy(List<CardItemForm>cardItemFormList){
        try{
            //重新添加的时候会将之前的策略全部清空
            vipStrategyMapper.deleteAllStrategies();
            for(CardItemForm cardItemForm:cardItemFormList){
                CardItem cardItem = new CardItem(cardItemForm);
                vipStrategyMapper.insertVIPStrategy(cardItem);
            }
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("添加失败");
        }
    }

    @Override
    public ResponseVO getAllStrategy(){
        try{
            List<CardItem>cardItems = vipStrategyMapper.selectAllStrategy();
            List<CardItemVO>cardItemVOS = this.cardItem2CardItemVO(cardItems);
            return ResponseVO.buildSuccess(cardItemVOS);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询失败");
        }
    }

    @Override
    public ResponseVO deleteStrategyByID(int id){
        try{
            vipStrategyMapper.deleteStrategyByID(id);
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("删除失败");
        }
    }

    @Override
    public ResponseVO changeStrategy(int id,CardItemForm cardItemForm){
        try{
            double target = cardItemForm.getTarget();
            double add = cardItemForm.getAdd();
            vipStrategyMapper.updateStrategy(id,target,add);
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("更新失败");
        }
    }

    private List<CardItemVO> cardItem2CardItemVO(List<CardItem>cardItemList){
        List<CardItemVO>cardItemVOS = new ArrayList<>();
        for(CardItem cardItem:cardItemList){
            CardItemVO cardItemVO = new CardItemVO(cardItem);
            cardItemVOS.add(cardItemVO);
        }
        return cardItemVOS;
    }
}
