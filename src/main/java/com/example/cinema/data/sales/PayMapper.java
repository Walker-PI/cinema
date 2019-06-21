package com.example.cinema.data.sales;
import com.example.cinema.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayMapper {
    //TODO:
    int insertOrder(Order order);

    //TODO:
    Order selectOrderByID(int orderId);

}
