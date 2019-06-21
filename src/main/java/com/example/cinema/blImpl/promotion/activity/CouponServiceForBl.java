package com.example.cinema.blImpl.promotion.activity;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface CouponServiceForBl {
    ResponseVO checkCoupon(int couponId, double fare);
    ResponseVO giveCoupon(int userId,int movieId);
    ResponseVO deleteCoupon(int userId,int couponId);
    List<Coupon> getCouponsInDate(int user_id);
}
