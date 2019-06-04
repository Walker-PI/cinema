package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CouponMapperTest {

    @Autowired
    CouponMapper couponMapper;
    @Test
    public void insertCoupon() {
    }

    @Test
    public void selectCouponByUser() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectProperCoupon() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        List<Coupon>coupons = couponMapper.selectProperCoupon(10,timestamp);
        for(Coupon coupon:coupons){
            System.out.println(coupon.getId());
        }
    }

    @Test
    public void insertCouponUser() {
    }

    @Test
    public void deleteCouponUser() {
    }

    @Test
    public void selectCouponByUserAndAmount() {
    }
}