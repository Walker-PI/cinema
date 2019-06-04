package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPStrategyService;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.CardItemForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vipStrategy")
public class VIPStrategyController {
    @Autowired
    VIPStrategyService vipStrategyService;

    @PostMapping("/publish")
    public ResponseVO publishVIPStrategy(@RequestBody CardItemForm cardItemForm){
        return vipStrategyService.publishVIPStrategy(cardItemForm);
    }

    @GetMapping("/get")
    public ResponseVO getAllStrategy(){
        return vipStrategyService.getAllStrategy();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseVO deleteStrategy(@PathVariable int id){
        return vipStrategyService.deleteStrategyByID(id);
    }

    @PostMapping("/change/{id}")
    public ResponseVO changeStrategy(@PathVariable int id, @RequestBody CardItemForm cardItemForm){
        return vipStrategyService.changeStrategy(id,cardItemForm);
    }
}
