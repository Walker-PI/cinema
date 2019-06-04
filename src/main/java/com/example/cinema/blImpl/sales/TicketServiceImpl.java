
package com.example.cinema.blImpl.sales;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.RefundStrategyServiceForBl;
import com.example.cinema.blImpl.promotion.VIPServiceForBl;
import com.example.cinema.blImpl.promotion.CouponServiceForBl;
import com.example.cinema.config.AlipayConfig;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponServiceForBl couponService;
    @Autowired
    VIPServiceForBl vipService;
    @Autowired
    RefundStrategyServiceForBl refundStrategyService;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        try {
            List<SeatForm> seats = ticketForm.getSeats();
            List<Ticket> tickets = new ArrayList<>();
            if (seats.size() == 0) return ResponseVO.buildFailure("选票数不能为0");
            else {
                int userid = ticketForm.getUserId();
                int scheduleid = ticketForm.getScheduleId();
                for (SeatForm seat : seats) {
                    Ticket ticket = new Ticket();
                    ticket.setScheduleId(scheduleid);
                    ticket.setUserId(userid);
                    ticket.setColumnIndex(seat.getColumnIndex());
                    ticket.setRowIndex(seat.getRowIndex());
                    ticket.setState(0);
                    ticket.setTicketingState(0);
                    Ticket temp = ticketMapper.selectTicketByScheduleIdAndSeat(scheduleid, seat.getColumnIndex(), seat.getRowIndex());
                    if (temp != null) {
                        return ResponseVO.buildFailure("选取的座位已经被预定！");
                    }
                    tickets.add(ticket);
                    ticketMapper.insertTicket(ticket);

                }
                return ResponseVO.buildSuccess(this.getOrderForm(tickets));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }


    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.stream().forEach(ticket -> {
                seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(ticketMapper.selectTicketByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("用户不存在");
        }
    }


    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        try {
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            int userId = temp.getUserId();
            int scheduleId = temp.getScheduleId();
            ScheduleItem scheduleItem = scheduleService.getScheduleItemById(scheduleId);
            int movieId = scheduleItem.getMovieId();
            ResponseVO vipResponseVO = vipService.getCardByUserId(userId);
            if (vipResponseVO.getSuccess()) {
                VIPCard vipCard = (VIPCard) vipResponseVO.getContent();
                ResponseVO completeVO = this.getPrice(id, couponId);
                if (completeVO.getSuccess()) {
                    double fare = (double) completeVO.getContent();
                    double balance = vipCard.getBalance();
                    balance = balance - fare;
                    if (balance < 0) return ResponseVO.buildFailure("余额不足，请充值");

                    //买票实际所花金额
                    double realFare = getRealFare(id);

                    //更新余额
                    for (int ticket_id : id) {
                        ticketMapper.updateTicketState(ticket_id, 1);

                        //是否使用了优惠券  使用了就更新use_coupon = 1
                        if(realFare > fare)ticketMapper.updateUseCoupon(ticket_id, 1);
                    }
                    vipService.updateBalance(vipCard.getId(), balance);
                    this.giveCoupon(userId,movieId);
                    //删除优惠券
                    couponService.deleteCoupon(userId,couponId);
                    return ResponseVO.buildSuccess();
                } else {
                    return ResponseVO.buildFailure(completeVO.getMessage());
                }
            } else {
                return ResponseVO.buildFailure("会员卡不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            for (int ticket_id : id) {
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if (ticket == null) return ResponseVO.buildFailure("取消了未锁座的票!");
                if (ticket.getState() == 0) ticketMapper.deleteTicket(ticket_id);
                else {
                    return ResponseVO.buildFailure("不能取消已购买或已失效的票!");
                }
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO getPrice(List<Integer> id, int couponId) {
        try {
            double sum = this.getRealFare(id);
            ResponseVO check_responseVO = couponService.checkCoupon(couponId, sum);

            if (check_responseVO.getSuccess() == true) {
                sum = sum - (double) check_responseVO.getContent();
            }
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            return ResponseVO.buildSuccess(sum);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO giveCoupon(int userId,int movieId) {
        try {
            couponService.giveCoupon(userId,movieId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCanRefund(List<Integer> ticketId) {
        try {
            if (ticketId.size() == 0) return ResponseVO.buildFailure("请选择要退的票");

            List<Integer> ticketState = new ArrayList<>();

            /**
             *得到退票策略
             */
            List<RefundStrg> refundStrgList = refundStrategyService.getAllRefundStrategy();

            //如果没有退票策略就让保证电影开场之前都能退吧  否则就按设定的最晚时间作为能退票时间
            long maxMinutes = 0;
            if(refundStrgList != null && refundStrgList.size() != 0){
                maxMinutes = refundStrgList.get(refundStrgList.size() - 1).getEndMinute();
            }

            for (int ticket_id : ticketId) {
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if(ticket == null)ticketState.add(0); //没有这张票不能退
                else if (ticket.getState() != 1) ticketState.add(0);  //没有购买成功不能退
                else if(ticket.getUseCoupon() == 1)ticketState.add(0); //使用优惠券不能退
                else if(ticket.getTicketingState() == 1)ticketState.add(0);  //已经出票不能退
                else {
                    ScheduleItem scheduleItem = scheduleService.getScheduleItemById(ticket.getScheduleId());
                    long minutesDiff = (new Date().getTime() - scheduleItem.getStartTime().getTime()) / (1000 * 60);
                    if (minutesDiff > maxMinutes) ticketState.add(0);  //超过规定时间也不能退
                    else ticketState.add(1);
                }
            }
            return ResponseVO.buildSuccess(ticketState);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getRefund(List<Integer> ticketId) {
        try {
            if (ticketId.size() == 0) return ResponseVO.buildFailure("请选择要退的票");

            /**
             *得到退票策略
             */
            List<RefundStrg> refundStrgList = refundStrategyService.getAllRefundStrategy();

            double refund = 0.0;
            for (int ticket_id : ticketId) {
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                ScheduleItem scheduleItem = scheduleService.getScheduleItemById(ticket.getScheduleId());
                long minutesDiff = (new Date().getTime() - scheduleItem.getStartTime().getTime()) / (1000 * 60);

                //如果没有退票策略就全额退吧
                double percent = 0.0;
                if(refundStrgList != null && refundStrgList.size() != 0){
                    //不必考虑越界,因为能够退票的都在退票范围内
                    int index = 0;
                    while(minutesDiff > refundStrgList.get(index).getEndMinute())index ++;
                    //考虑如果处理太多退票但是时间一直在流走导致溢出
                    if(index >= refundStrgList.size()) index = refundStrgList.size() - 1;

                    percent = refundStrgList.get(index).getPercent();
                }
                refund = scheduleItem.getFare() * (1 - percent);
            }
            refund = (double) Math.round(refund * 100) / 100;
            return ResponseVO.buildSuccess(refund);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO completeRefund(List<Integer> ticketId, double refund) {
        try {
            if (ticketId.size() == 0) return ResponseVO.buildFailure("请选择要退的票");
            Ticket ticket = ticketMapper.selectTicketById(ticketId.get(0));
            for (int ticket_id : ticketId)
                ticketMapper.deleteTicket(ticket_id);

            VIPCard vipCard = vipService.getVIPCardByUserId(ticket.getUserId());

            //用户没有VIP_CRAD送一张状态为0：普通卡
            if(vipCard == null) vipCard = vipService.addCard(ticket.getUserId(), 0);

            vipService.updateBalance(vipCard.getId(), vipCard.getBalance() + refund);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public OrderFormVO getOrderForm(List<Ticket> tickets) {
        //先把PO转化为VO
        OrderFormVO orderFormVO = new OrderFormVO();
        List<TicketVO> ticketVOList = new ArrayList<>();
        List<CouponVO> couponVOList = new ArrayList<>();
        int total = 0;
        for (Ticket ticket : tickets) {
            //将Ticket转化为TicketVO
            TicketVO ticketVO = new TicketVO();
            ticketVO.setId(ticket.getId());
            ticketVO.setUserId(ticket.getUserId());
            ticketVO.setScheduleId(ticket.getScheduleId());
            ticketVO.setColumnIndex(ticket.getColumnIndex());
            ticketVO.setRowIndex(ticket.getRowIndex());
            ticketVO.setState(ticket.getState() + "");
            ticketVO.setTime(ticket.getTime());
            ticketVOList.add(ticketVO);

            //算总的定价
            ScheduleItem schedule = scheduleService.getScheduleItemById(ticket.getScheduleId());
            total += schedule.getFare();
        }
        //得到当前时间当前用户能用的优惠券
        int user_id = tickets.get(0).getUserId();
        List<Coupon> coupons = couponService.getCouponsInDate(user_id);
        for (Coupon coupon : coupons) {
            CouponVO couponVO = new CouponVO();
            couponVO.setId(coupon.getId());
            couponVO.setDescription(coupon.getDescription());
            couponVO.setName(coupon.getName());
            couponVO.setTargetAmount(coupon.getTargetAmount());
            couponVO.setDiscountAmount(coupon.getDiscountAmount());
            couponVO.setStartTime(coupon.getStartTime());
            couponVO.setEndTime(coupon.getEndTime());
            couponVOList.add(couponVO);
        }
        orderFormVO.setTotal(total);
        orderFormVO.setCouponVOList(couponVOList);
        orderFormVO.setTicketVOList(ticketVOList);
        return orderFormVO;
    }

    @Override
    public ResponseVO makePayRequest(List<Integer> id, int couponId) {

        ResponseVO responseVO = this.getPrice(id, couponId);
        if (!responseVO.getSuccess()) return ResponseVO.buildFailure("查找不到所购票");
        double fare = (double) responseVO.getContent();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient
                (AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no;
        //付款金额，必填
        String total_amount;
        //订单名称，必填
        String subject;
        //商品描述，可空
        String body = "0";
        try {
            total_amount = String.valueOf(fare);
            subject = "电影票";
            if(this.getRealFare(id) > fare)body = "1";

            //在数据库存储订单,0表示订单未完成，1表示已完成
            Order order = new Order(subject,body,fare,0);
            ticketMapper.insertOrder(order);
            int orderId=order.getId();
            out_trade_no = String.valueOf(orderId);
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求，并以HTML形式展示
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayRequest);
            String head = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'</head>";
            String result = response.getBody();
            String bottom = "<body></body></html>";

            //在数据库中存储这条订单相关的票
            ticketMapper.insertTicketsInOrder(orderId,id);
            return ResponseVO.buildSuccess(head + result + bottom);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO issueTicket(List<Integer> ticketId){
        try{
            if(ticketId.size() == 0)return ResponseVO.buildFailure("失败");
            for (int ticket_id: ticketId){
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if(ticket.getTicketingState() == 0)
                    ticketMapper.updateTicketingState(ticket_id, 1);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public void mentionCompleteByAli(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取订单号
            String trade_status = new String(request.getParameter("trade_status")
                    .getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("通知到了");
            if(trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){

                //对订单做的处理
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
                String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
                System.out.println("订单号:"+trade_no);
                int orderId = Integer.parseInt(trade_no);
                ticketMapper.updateOrderStatus(orderId,1);
                List<Ticket>tickets = ticketMapper.selectTicketByOrder(orderId);
                for(Ticket ticket:tickets){
                    int ticketId = ticket.getId();
                    ticketMapper.updateTicketState(ticketId,1);
                    if("1".equals(body))ticketMapper.updateUseCoupon(ticketId, 1);
                }

                System.out.println("到这步就问题不大了");
                response.getWriter().write("success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取实际价格
     * @param ticketId
     * @return
     */
    public double getRealFare(List<Integer> ticketId){
        double sumFare = 0;
        for (int ticket_id : ticketId) {
            Ticket ticket = ticketMapper.selectTicketById(ticket_id);
            ScheduleItem item = scheduleService.getScheduleItemById(ticket.getScheduleId());
            sumFare += item.getFare();
        }
        return sumFare;
    }
}
