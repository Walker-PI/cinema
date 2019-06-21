package com.example.cinema.bl.promotion;

import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;



/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId,double fare);

    ResponseVO getCardById(int id);

    ResponseVO getVIPInfo();

    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);

    ResponseVO upgradeCard(int userId,double fare);

    ResponseVO makeChargeRequest(VIPCardForm vipCardForm);

    ResponseVO makeBuyCardRequest(int userId,double fare);

    ResponseVO makeUpgradeCardRequest(int userId,double fare);
}
