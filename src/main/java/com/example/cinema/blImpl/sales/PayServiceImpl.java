package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.PayService;
import com.example.cinema.blImpl.promotion.vip.PayServiceForBl;
import com.example.cinema.data.sales.PayMapper;
import com.example.cinema.po.Order;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService, PayServiceForBl {

    @Autowired
    PayMapper payMapper;

    @Override
    public ResponseVO getOrderTypeByID(int orderId){
        try{
            Order order = payMapper.selectOrderByID(orderId);
            if(order!=null)return ResponseVO.buildSuccess(order.getContent());
            else return ResponseVO.buildFailure("未找到指定订单");
        }catch(Exception e){
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO insertOrder(Order order){
        try{
            payMapper.insertOrder(order);
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }
}
