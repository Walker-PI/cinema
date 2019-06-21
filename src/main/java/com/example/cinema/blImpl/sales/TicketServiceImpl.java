
package com.example.cinema.blImpl.sales;

import com.example.cinema.blImpl.alipayHandle.AliPayHandle;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.management.refundStrategy.RefundStrategyServiceForBl;
import com.example.cinema.blImpl.promotion.vip.VIPServiceForBl;
import com.example.cinema.blImpl.promotion.activity.CouponServiceForBl;
import com.example.cinema.blImpl.user.HistoryServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    @Autowired
    HistoryServiceForBl historyService;

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
                return ResponseVO.buildSuccess(tickets);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO generateOrder(List<Integer> ticketIds){
        try{
            List<Ticket>tickets = new ArrayList<>();
            for(int id:ticketIds){
                Ticket ticket = ticketMapper.selectTicketById(id);
                if(ticket==null)return ResponseVO.buildFailure("未查到对应票");
                tickets.add(ticket);
            }
            return ResponseVO.buildSuccess(this.getOrderForm(tickets));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("生成订单失败");
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
            String movieName = scheduleItem.getMovieName();
            ResponseVO vipResponseVO = vipService.getCardByUserId(userId);
            if (vipResponseVO.getSuccess()) {
                VIPCard vipCard = (VIPCard) vipResponseVO.getContent();
                ResponseVO completeVO = this.getPrice(id, couponId);
                if (completeVO.getSuccess()) {
                    //买票实际所花金额
                    double realFare = getRealFare(id);
                    double fare = (double) completeVO.getContent();
                    double balance = vipCard.getBalance();
                    balance = balance - fare;
                    if (balance < 0) return ResponseVO.buildFailure("余额不足，请充值");


                    //更新余额
                    for (int ticket_id : id) {
                        ticketMapper.updateTicketState(ticket_id, 1);

                        //是否使用了优惠券  使用了就更新use_coupon = 1
                        if(realFare > fare)ticketMapper.updateUseCoupon(ticket_id, couponId);
                        if(!this.giveCoupon(userId,movieId))
                            return ResponseVO.buildFailure("优惠券赠送失败");
                    }
                    vipService.updateBalance(vipCard.getId(), balance);
                    //删除优惠券
                    couponService.deleteCoupon(userId,couponId);

                    //添加历史记录
                    Timestamp timestamp = new Timestamp(new Date().getTime());
                    History history = new History(1,timestamp,fare,"ticket",userId);
                    historyService.insertTicketHistory(history,movieName,id);
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

    //按照前端要求添加了scheduleID和movieID
    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            int scheduleId = temp.getScheduleId();
            ScheduleItem scheduleItem = scheduleService.getScheduleItemById(scheduleId);
            int movieId = scheduleItem.getMovieId();
            CancelTicketForm cancelTicketForm = new CancelTicketForm(scheduleId,movieId);
            for (int ticket_id : id) {
                Ticket ticket = ticketMapper.selectTicketById(ticket_id);
                if (ticket == null) return ResponseVO.buildFailure("取消了未锁座的票!");
                if (ticket.getState() == 0) ticketMapper.deleteTicket(ticket_id);
                else {
                    return ResponseVO.buildFailure("不能取消已购买或已失效的票!");
                }
            }
            return ResponseVO.buildSuccess(cancelTicketForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    //判断电影票是否能退票
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
                else if (ticket.getUseCoupon() > 0)ticketState.add(0); //使用优惠券不能退
                else if (ticket.getTicketingState() == 1)ticketState.add(0);  //已经出票不能退
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

    //获取退款金额
    @Override
    public ResponseVO getRefund(List<Integer> ticketId) {
        try {
            if (ticketId.size() == 0) return ResponseVO.buildFailure("请选择要退的票");
            double refund = calculateRefund(ticketId);
            return ResponseVO.buildSuccess(refund);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    //完成退款操作
    @Override
    public ResponseVO completeRefund(List<Integer> ticketId) {
        try {
            if (ticketId.size() == 0) return ResponseVO.buildFailure("请选择要退的票");
            Ticket ticket = ticketMapper.selectTicketById(ticketId.get(0));
            for (int ticket_id : ticketId)
                ticketMapper.updateTicketState(ticket_id,3);

            VIPCard vipCard = vipService.getVIPCardByUserId(ticket.getUserId());

            //用户没有VIP_CRAD送一张状态为0：普通卡
            if(vipCard == null) vipCard = vipService.addCard(ticket.getUserId(), 0);

            double refund=calculateRefund(ticketId);
            vipService.updateBalance(vipCard.getId(), vipCard.getBalance() + refund);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    //获取订单信息，用于前端渲染
    private OrderFormVO getOrderForm(List<Ticket> tickets) {
        //先把PO转化为VO
        OrderFormVO orderFormVO = new OrderFormVO();
        List<TicketVO> ticketVOList = new ArrayList<>();
        List<CouponVO> couponVOList = new ArrayList<>();
        int total = 0;
        Ticket temp = tickets.get(0);
        //默认一张订单只能包含同一场次的电影
        int scheduleId = temp.getScheduleId();
        ScheduleItem scheduleItem = scheduleService.getScheduleItemById(scheduleId);
        String  movieName = scheduleItem.getMovieName() ;
        String hallName = scheduleItem.getHallName();
        double singlePrice = scheduleItem.getFare();
        Date startTime = scheduleItem.getStartTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String scheduleTime = formatter.format(startTime);
        String generateTime = formatter.format(temp.getTime());
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
        orderFormVO.setMovieName(movieName);
        orderFormVO.setHallName(hallName);
        orderFormVO.setSinglePrice(singlePrice);
        orderFormVO.setScheduleTime(scheduleTime);
        orderFormVO.setGenerateTime(generateTime);
        return orderFormVO;
    }

    @Override
    public ResponseVO makePayRequest(List<Integer> id, int couponId) {

        ResponseVO responseVO = this.getPrice(id, couponId);
        Ticket ticket = ticketMapper.selectTicketById(id.get(0));
        int scheduleId = ticket.getScheduleId();
        ScheduleItem scheduleItem = scheduleService.getScheduleItemById(scheduleId);
        String movieName = scheduleItem.getMovieName();
        if (!responseVO.getSuccess()) return ResponseVO.buildFailure("查找不到所购票");
        double fare = (double) responseVO.getContent();

        String out_trade_no;
        //付款金额，设置为实际付的钱
        String total_amount = String.valueOf(fare);
        //订单名称，设置为电影的名字
        String subject = movieName;
        //商品描述，可空
        String body = this.getRealFare(id) > fare?""+couponId:"0";
        //类型，"ticket"表示是买票类型
        String type = "ticket";
        //在数据库存储订单,0表示订单未完成，1表示已完成
        Order order = new Order(subject,type,fare,0);
        ticketMapper.insertOrder(order);
        int orderId=order.getId();
        out_trade_no = "MTS"+orderId;

        String resultHTML = AliPayHandle.executePayRequest(out_trade_no,total_amount,subject,body);

        //在数据库中存储这条订单相关的票
        ticketMapper.insertTicketsInOrder(orderId,id);
        return ResponseVO.buildSuccess(resultHTML);

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


    public void mentionCompleteByAli(HttpServletRequest request){
        try {
                //对订单做的处理
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
                String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
                String fare = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
                String movieName = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
                out_trade_no = out_trade_no.substring(3);
                int orderId = Integer.parseInt(out_trade_no);
                ticketMapper.updateOrderStatus(orderId,1);
                List<Ticket>tickets = ticketMapper.selectTicketByOrder(orderId);

                List<Integer>ticketIds = new ArrayList<>();

                int userId = tickets.get(0).getUserId();
                for(Ticket ticket:tickets){
                    int ticketId = ticket.getId();
                    ticketIds.add(ticketId);
                    ticketMapper.updateTicketState(ticketId,1);
                    if(!"0".equals(body))ticketMapper.updateUseCoupon(ticketId, Integer.parseInt(body));
                }

                //删除优惠券还没写

                //存储历史记录
                Timestamp timestamp = new Timestamp(new Date().getTime());
                History history = new History(0,timestamp,Double.parseDouble(fare),"ticket",userId);
                historyService.insertTicketHistory(history,movieName,ticketIds);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 根据票和优惠券计算订单金额
     * @param id
     * @param couponId
     * @return
     */
    private ResponseVO getPrice(List<Integer> id, int couponId) {
        try {
            double sum = this.getRealFare(id);
            ResponseVO check_responseVO = couponService.checkCoupon(couponId, sum);

            if (check_responseVO.getSuccess()) {
                sum = sum - (double) check_responseVO.getContent();
            }
            Ticket temp = ticketMapper.selectTicketById(id.get(0));
            return ResponseVO.buildSuccess(sum);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    /**
     * 购票后赠送优惠券
     * @param userId
     * @param movieId
     * @return
     */
    private boolean giveCoupon(int userId,int movieId) {
        try {
            couponService.giveCoupon(userId,movieId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取实际价格
     * @param ticketId
     * @return
     */
    private double getRealFare(List<Integer> ticketId){
        double sumFare = 0;
        for (int ticket_id : ticketId) {
            Ticket ticket = ticketMapper.selectTicketById(ticket_id);
            ScheduleItem item = scheduleService.getScheduleItemById(ticket.getScheduleId());
            sumFare += item.getFare();
        }
        return sumFare;
    }

    /**
     * 计算退款金额
     * @param ticketId
     * @return
     */
    private double calculateRefund(List<Integer> ticketId){

        //得到退票策略
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
            refund += scheduleItem.getFare() * (1 - percent);
        }
        refund = (double) Math.round(refund * 100) / 100;
        return refund;
    }
}

