package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.blImpl.alipayHandle.AliPayHandle;
import com.example.cinema.blImpl.user.HistoryServiceForBl;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.History;
import com.example.cinema.po.Order;
import com.example.cinema.vo.CardItemVO;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService, VIPServiceForBl {
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    HistoryServiceForBl historyService;
    @Autowired
    PayServiceForBl payServiceForBl;
    @Autowired
    VIPStrategyServiceForBl vipStrategyService;

    @Override
    public ResponseVO addVIPCard(int userId,double fare) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        vipCard.setVipType(1);  //买卡都是会员卡（有充值优惠）

        //添加历史记录
        Timestamp timestamp = new Timestamp(new Date().getTime());
        History history = new History(0,timestamp,fare,"card",userId);
        historyService.insertCardHistory(history);
        try {
            int id = vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public VIPCard addCard(int userId, int vip_type) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        vipCard.setVipType(0);   //退票时产生的普通卡（没有充值优惠）
        vipCardMapper.insertOneCard(vipCard);
        return vipCardMapper.selectCardByUserId(userId);
    }

    @Override
    public ResponseVO upgradeCard(int userId,double fare) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);

            if(vipCard == null)return ResponseVO.buildFailure("用户还没有普通卡");
            else if(vipCard.getVipType() == 1)return ResponseVO.buildFailure("已经是升级过的会员卡");
            else {
                vipCardMapper.updateVipType(userId);

                //添加历史记录
                Timestamp timestamp = new Timestamp(new Date().getTime());
                History history = new History(0,timestamp,fare,"upCard",userId);
                historyService.insertCardHistory(history);
                return ResponseVO.buildSuccess();
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("升级失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo() {
        VIPInfoVO vipInfoVO = new VIPInfoVO();
        vipInfoVO.setDescription(VIPCard.description);
        vipInfoVO.setPrice(VIPCard.price);
        return ResponseVO.buildSuccess(vipInfoVO);
    }


    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {
        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        double amount = vipCardForm.getAmount();
        double add=0;
        //根据策略送钱
        ResponseVO response = vipStrategyService.getAllStrategy();
        if(response.getSuccess()&&vipCard.getVipType()!=0){
            List<CardItemVO>cardItemVOS = (List<CardItemVO>) response.getContent();
            for (CardItemVO cardItemVO:cardItemVOS){
                double target = cardItemVO.getTarget();
                if(amount>=target)add = cardItemVO.getAdd();
            }
        }
        amount+=add;
        vipCard.setBalance(vipCard.getBalance() + amount);
        try {
            int cardId = vipCard.getId();
            double fare = vipCardForm.getAmount();
            int userId = vipCard.getUserId();
            Timestamp timestamp = new Timestamp(new Date().getTime());
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            //添加历史记录
            History history = new History(0,timestamp,fare,"charge",userId);
            historyService.insertChargeHistory(history,vipCard.getBalance());

            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO updateBalance(int id, double balance){
        try{
            vipCardMapper.updateCardBalance(id, balance);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("更新余额失败");
        }
    }

    @Override
    public VIPCard getVIPCardByUserId(int userId) {
        return vipCardMapper.selectCardByUserId(userId);
    }

    @Override
    public ResponseVO makeBuyCardRequest(int userId,double fare){
        try{
            String resultHTML = AliCardRequest("购买会员卡","buyCard",userId,fare);
            return ResponseVO.buildSuccess(resultHTML);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("买卡失败");
        }
    }

    @Override
    public ResponseVO makeUpgradeCardRequest(int userId,double fare) {
        try {
            String resultHTML =AliCardRequest("升级会员卡","upgradeCard",userId,fare);
            return ResponseVO.buildSuccess(resultHTML);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("买卡失败");
        }
    }

    @Override
    public ResponseVO makeChargeRequest(VIPCardForm vipCardForm){
        try{
            String resultHTML = AliCardRequest("充值","charge",vipCardForm.getVipId(),vipCardForm.getAmount());
            return ResponseVO.buildSuccess(resultHTML);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("充值失败");
        }
    }


    /**
     * 发起通过支付宝的会员卡操作
     * @param subject:订单名称
     * @param type:卡操作类型，包含购卡、充值、升级卡
     * @param describe:商品描述,购卡和升级卡设计为userID，充值设计为vipID
     * @param fare
     * @return
     */
    private String AliCardRequest(String subject,String type,int describe,double fare){
        String out_trade_no;
        //付款金额，设置为实际付的钱
        String total_amount = String.valueOf(fare);
        //商品描述
        String body = String.valueOf(describe);
        //在数据库存储订单,0表示订单未完成，1表示已完成
        Order order = new Order(subject, type, fare, 0);
        payServiceForBl.insertOrder(order);
        int orderId = order.getId();
        out_trade_no = "TMS" + orderId;
        return AliPayHandle.executePayRequest(out_trade_no, total_amount, subject, body);
    }

}

