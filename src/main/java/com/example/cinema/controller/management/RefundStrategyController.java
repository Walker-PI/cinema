package com.example.cinema.controller.management;

import com.example.cinema.bl.management.RefundStrategyService;
import com.example.cinema.vo.RefundStrgVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO lms
/**
 * 退票策略
 */
@RestController
@RequestMapping("/refund")
public class RefundStrategyController {

    @Autowired
    private RefundStrategyService refundStrategyService;

    @GetMapping("/get")
    public ResponseVO getAllStrategy(){
        return refundStrategyService.getAllStrategy();
    }

    @PostMapping("/update")
    public ResponseVO updateAllStrategy(@RequestBody List<RefundStrgVO> refundStrgVOList){
        System.out.println("refund ticket");
        return refundStrategyService.updateAllStrategy(refundStrgVOList);
    }

}
