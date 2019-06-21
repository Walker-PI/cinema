package com.example.cinema.blImpl.alipayHandle;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.example.cinema.config.AlipayConfig;

public class AliPayHandle {

    private static AlipayClient alipayClient =new DefaultAlipayClient
            (AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);


    public static String executePayRequest(String out_trade_no,
            String total_amount, String subject, String body){
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求，并以HTML形式展示
        AlipayTradePagePayResponse response;
        try {
            response = alipayClient.pageExecute(aliPayRequest);
            String head = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'</head>";
            String result = response.getBody();
            String bottom = "<body></body></html>";
            return head+result+bottom;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
