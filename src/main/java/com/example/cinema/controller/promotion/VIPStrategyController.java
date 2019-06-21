package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipStrategy")
public class VIPStrategyController {
    @Autowired
    VIPStrategyService vipStrategyService;

    //添加的时候会将之间的策略全部删除
    @PostMapping("/publish")
    public ResponseVO publishVIPStrategy(@RequestBody List<CardItemForm> cardItemFormList){
        return vipStrategyService.publishVIPStrategy(cardItemFormList);
    }

    @GetMapping("/get")
    public ResponseVO getAllStrategy(){
        return vipStrategyService.getAllStrategy();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVO deleteStrategy(@PathVariable int id){
        return vipStrategyService.deleteStrategyByID(id);
    }

    //改的时候只能一条一条的改
    @PostMapping("/change/{id}")
    public ResponseVO changeStrategy(@PathVariable int id, @RequestBody CardItemForm cardItemForm){
        return vipStrategyService.changeStrategy(id,cardItemForm);
    }
}
