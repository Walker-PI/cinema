package com.example.cinema.bl.sales;

import com.example.cinema.vo.ResponseVO;


public interface PayService {
    ResponseVO getOrderTypeByID(int orderId);
}
