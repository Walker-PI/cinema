package com.example.cinema.blImpl.sales;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.cinema.config.AlipayConfig;
import com.example.cinema.po.Order;
import com.example.cinema.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceImplTest {

    @Test
    public void makePayRequest() {
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
        String body;
        try {
            total_amount = String.valueOf(100);
            subject = "电影票";
            body = "万达影城电影票";

            //在数据库存储订单,0表示订单未完成，1表示已完成
            Order order = new Order(subject,body,100,0);
            int orderId=order.getId();
            out_trade_no = String.valueOf(orderId);
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求，并以HTML形式展示
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayRequest);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}