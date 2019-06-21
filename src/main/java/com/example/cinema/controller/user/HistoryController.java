package com.example.cinema.controller.user;

import com.example.cinema.bl.user.HistoryService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;
    /**
     * 通过userId查找他所有的消费记录，包括买卡、充值、买票
     * 成功时返回一个List<HistoryVO>
     * @param userId
     * @return
     */
    @GetMapping("/all/{userId}")
    public ResponseVO getAllHistory(@PathVariable int userId){
        return historyService.getAllHistory(userId);
    }

    /**
     * 根据在getAllHistory方法里返回的historyId和type查找某项历史记录的具体信息
     * 如果type是"card"时，没有多余信息，建议别调
     * 是"ticket"时，会把买的票的电影名传过去
     * 是"charge"时，会把你的余额传过去
     * 需要多余的信息建议用sessionStorage
     * @param historyId,type
     * @return
     */
    @GetMapping("/item")
    public ResponseVO getItemHistory(@RequestParam int historyId,@RequestParam String type){
        return historyService.getItemHistory(historyId,type);
    }
}

