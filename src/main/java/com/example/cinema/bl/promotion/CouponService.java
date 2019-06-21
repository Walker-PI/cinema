package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

/**
 * Created by liying on 2019/4/17
 */
public interface CouponService {

    ResponseVO getCouponsByUser(int userId);

    ResponseVO addCoupon(CouponForm couponForm);

    ResponseVO issueCoupon(int couponId,int userId);

    ResponseVO issueCoupons(int couponId,List<Integer>userIds);

    /**
     * 成功时返回一个VIPUserVOList
     * @param spend
     * @return
     */
    ResponseVO getVIPBySpend(double spend);
}
