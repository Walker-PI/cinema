package com.example.cinema.controller.sales;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.config.AlipayConfig;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestParam List<Integer> ticketId, @RequestParam int couponId){
        return ticketService.completeByVIPCard(ticketId,couponId);
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }

    //按照前端要求根据ticketID的列表返回OrderForm
    @PostMapping("/generateOrder")
    public ResponseVO generateOrder(@RequestParam List<Integer> ticketIds){return ticketService.generateOrder(ticketIds);}

    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestParam List<Integer> ticketId, @RequestParam int couponId){
        return ticketService.makePayRequest(ticketId,couponId);
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId){
        return ticketService.getBySchedule(scheduleId);
    }

    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }

    @GetMapping("/get/canRefund")
    public ResponseVO getCanRefund(@RequestParam List<Integer> ticketId){
        return ticketService.getCanRefund(ticketId);
    }

    @GetMapping("/get/refund")
    public ResponseVO getRefund(@RequestParam List<Integer> ticketId){
        return ticketService.getRefund(ticketId);
    }

    @PostMapping("/complete/refund")
    public ResponseVO completeRefund(@RequestParam List<Integer> ticketId){
        return ticketService.completeRefund(ticketId);
    }


    /**
     * 出票操作
     * @param ticketId
     * @return
     */
    @PostMapping("/issue")
    public ResponseVO issueTicket(@RequestParam List<Integer> ticketId){
        return ticketService.issueTicket(ticketId);
    }


}
