package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.blImpl.promotion.activity.CouponServiceForBl;
import com.example.cinema.blImpl.user.AccountServiceForBl;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    AccountServiceForBl accountService;

    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(couponMapper.selectCouponByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO issueCoupons(int couponId,List<Integer>userIds){
        try {
            for(int userId:userIds){
                couponMapper.insertCouponUser(couponId,userId);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO checkCoupon(int couponId, double fare){
        try{
            Coupon coupon = couponMapper.selectById(couponId);
            if(coupon==null)return ResponseVO.buildFailure("无可用优惠券");
            if(fare<coupon.getTargetAmount())return ResponseVO.buildFailure("未达到指定金额");
            Date startTime = coupon.getStartTime();
            Date endTime = coupon.getEndTime();
            Date today = new Date();
            if(today.getTime()<startTime.getTime()||today.getTime()>endTime.getTime()){
                return ResponseVO.buildFailure("不在使用期限内");
            }
            return ResponseVO.buildSuccess(coupon.getDiscountAmount());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO giveCoupon(int userId,int movieId){
        //两个条件：在时间内且参与指定电影
        try{
            Timestamp now = new Timestamp(new Date().getTime());
            List<Coupon>coupons = couponMapper.selectProperCoupon(movieId,now);
            if(coupons.size()==0)return ResponseVO.buildSuccess("无可赠送优惠券");
            else{
                for(Coupon coupon:coupons){
                    this.issueCoupon(coupon.getId(),userId);
                }
            }
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteCoupon(int userId,int couponId){
        try{
            couponMapper.deleteCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        }catch(Exception e){
            return ResponseVO.buildFailure("用户无可用优惠券");
        }
    }

    @Override
    public List<Coupon> getCouponsInDate(int user_id){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        List<Coupon>coupons = couponMapper.selectCouponByUser(user_id);
        List<Coupon>can_use_coupons = new ArrayList<>();
        for(Coupon coupon:coupons){
            if(timestamp.before(coupon.getEndTime())
                    &&timestamp.after(coupon.getStartTime())){
                can_use_coupons.add(coupon);
            }
        }
        return can_use_coupons;
    }

    @Override
    public ResponseVO getVIPBySpend(double spend){
        try{
            List<VIPUserVO>vipUserVOS = accountService.getVIPUserListBySpendAmount(spend);
            //可能为空，为空的时候依旧返回success
            return ResponseVO.buildSuccess(vipUserVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("");
        }
    }
}
