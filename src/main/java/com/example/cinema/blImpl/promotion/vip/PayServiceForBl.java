package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.po.Order;
import com.example.cinema.vo.ResponseVO;

public interface PayServiceForBl {
    /**
     * 插入订单
     * @param order
     */
    ResponseVO insertOrder(Order order);
}
