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
    public ResponseVO addVIP(@RequestParam int userId, @RequestParam double fare) {
        return vipService.makeBuyCardRequest(userId, fare);
    }

    @PostMapping("/add/directly")
    public ResponseVO addVIPDirectly(@RequestParam int userId, @RequestParam double fare) {
        return vipService.addVIPCard(userId, fare);
    }

    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId) {
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("/getVIPInfo")
    public ResponseVO getVIPInfo() {
        return vipService.getVIPInfo();
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm) {
        return vipService.makeChargeRequest(vipCardForm);
    }

    @PostMapping("/charge/directly")
    public ResponseVO chargeDirectly(@RequestBody VIPCardForm vipCardForm) {
        return vipService.charge(vipCardForm);
    }

    /**
     * 普通卡升级
     *
     * @param userId
     * @return
     */
    @PostMapping("/upgrade")
    public ResponseVO upgradeCard(@RequestParam int userId, @RequestParam double fare) {
        return vipService.makeUpgradeCardRequest(userId, fare);
    }

    @PostMapping("/upgrade/directly")
    public ResponseVO upgreadeCardDirectly(@RequestParam int userId,@RequestParam double fare){
        return vipService.upgradeCard(userId,fare);
    }
}
