package com.example.cinema.data.promotion;

import com.example.cinema.po.ChargeItem;
import com.example.cinema.po.VIPCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by liying on 2019/4/14.
 */
@Mapper
public interface VIPCardMapper {

    int insertOneCard(VIPCard vipCard);

    VIPCard selectCardById(int id);

    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    VIPCard selectCardByUserId(int userId);

    void updateVipType(@Param("userId") int userId);

    int insertChargeItem(ChargeItem chargeItem);

    List<ChargeItem>selectChargeItemsByUserId(int userId);
    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredChargeOrder();

}
