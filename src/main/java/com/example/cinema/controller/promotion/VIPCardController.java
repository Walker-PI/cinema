package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId){
        return vipService.addVIPCard(userId);
    }

    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("/getVIPInfo")
    public ResponseVO getVIPInfo(){
        return vipService.getVIPInfo();
    }

    /**
     * 获取VIP卡充值记录
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/getChargeHistory")
    public ResponseVO getChargeHistory(@PathVariable int userId){
        return vipService.getChargeHistory(userId);
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }


    /**
     * 普通卡升级
     * @param userId
     * @return
     */
    @PostMapping("/upgrade")
    public ResponseVO upgradeCard(@RequestParam int userId){
        return vipService.upgradeCard(userId);
    }

}
