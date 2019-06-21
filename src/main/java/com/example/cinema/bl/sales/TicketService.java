package com.example.cinema.bl.sales;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     * 锁座【增加票但状态为未付款】
     *
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    ResponseVO generateOrder(List<Integer> ticketId);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ResponseVO getBySchedule(int scheduleId);

    /**
     * 获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * 完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param id
     * @param couponId
     * @return
     */
    ResponseVO completeByVIPCard(List<Integer> id, int couponId);

    /**
     * 取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param id
     * @return
     */
    ResponseVO cancelTicket(List<Integer> id);


    /**
     * content是一个数组对应ticket能否退票【1能  0不能】
     * @return
     */
    ResponseVO getCanRefund(List<Integer> ticketId);

    /**
     * lms 用户退票后应该收取的手续费  退票规则还没有做

     * @param ticketId
     * @return
     */
    ResponseVO getRefund(List<Integer> ticketId);

    /**
     * 完成退票   需要根据用户使用的支付方式【1使用vip_card支付， 2使用支付宝支付】 退钱
     * @param ticketId
     * @return
     */
    ResponseVO completeRefund(List<Integer> ticketId);

    ResponseVO makePayRequest(List<Integer> id, int couponId);

    /**
     * 出票状态改为1
     * @param ticketId
     * @return
     */
    ResponseVO issueTicket(List<Integer> ticketId);

    void mentionCompleteByAli(HttpServletRequest request);

}
