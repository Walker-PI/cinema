package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }

    @PostMapping("{userIds}/issue")
    public ResponseVO issueCoupons(@PathVariable List<Integer>userIds,@RequestParam int couponId){
        return couponService.issueCoupons(couponId,userIds);
    }

    @GetMapping("/getVIP/{spend}")
    public ResponseVO getVIPBySpend(@PathVariable double spend){
        return couponService.getVIPBySpend(spend);
    }
}
