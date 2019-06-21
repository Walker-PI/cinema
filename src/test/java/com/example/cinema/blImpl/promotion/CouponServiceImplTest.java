package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPUserVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CouponServiceImplTest {

    @Autowired
    CouponService couponService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCouponsByUser() {
    }

    @Test
    public void addCoupon() {
    }

    @Test
    public void issueCoupon() {
    }

    @Test
    public void checkCoupon() {
    }

    @Test
    public void giveCoupon() {
    }

    @Test
    public void getCouponsInDate() {
    }

    @Test
    public void getVIPBySpend() {
        ResponseVO responseVO = couponService.getVIPBySpend(100);
        List<VIPUserVO>vipUserVOS = (List)responseVO.getContent();
        for(VIPUserVO vipUserVO:vipUserVOS){
            System.out.println(vipUserVO.getId());
            System.out.println(vipUserVO.getName());
            System.out.println(vipUserVO.getSpend());
        }
    }
}