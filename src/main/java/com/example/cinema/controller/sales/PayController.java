package com.example.cinema.controller.sales;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.sales.PayService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPCardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/notify")
public class PayController {

    @Autowired
    TicketService ticketService;
    @Autowired
    VIPService vipService;
    @Autowired
    PayService payService;

    @PostMapping("/pay")
    public void notifyPay(HttpServletRequest request, HttpServletResponse response){
        String type="";

        try {
            String trade_status = new String(request.getParameter("trade_status")
                    .getBytes("ISO-8859-1"),"UTF-8");
            if(trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
                out_trade_no = out_trade_no.substring(3);
                ResponseVO responseVO = payService.getOrderTypeByID(Integer.parseInt(out_trade_no));
                if(responseVO.getSuccess())type=(String)responseVO.getContent();
                String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");
                String fare = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
                if(type.equals("ticket")){
                    ticketService.mentionCompleteByAli(request);
                }
                else if(type.equals("charge")){
                    VIPCardForm vipCardForm = new VIPCardForm();
                    int vipId = Integer.parseInt(body);
                    int amount = (int)Double.parseDouble(fare);
                    vipCardForm.setVipId(vipId);
                    vipCardForm.setAmount(amount);
                    vipService.charge(vipCardForm);
                }
                else if(type.equals("buyCard")){
                    int userId = Integer.parseInt(body);
                    double amount = Double.parseDouble(fare);
                    vipService.addVIPCard(userId,amount);
                }
                else if(type.equals("upgradeCard")){
                    int userId = Integer.parseInt(body);
                    double amount = Double.parseDouble(fare);
                    vipService.upgradeCard(userId,amount);
                }
                response.getWriter().write("success");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
