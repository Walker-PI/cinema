package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.blImpl.management.hall.HallServiceImpl;
import com.example.cinema.vo.HallFormVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**影厅管理
 * @author fjj
 * @date 2019/4/12 1:59 PM
 */
@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseVO searchAllHall(){
        return hallService.searchAllHall();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseVO searchHallByID(@PathVariable int id){
        return hallService.searchHall(id);
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseVO addHall(@RequestBody HallFormVO hallFormVO){
        return hallService.addHall(hallFormVO);
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.DELETE)
    public ResponseVO deleteHall(@RequestParam int id){
        return hallService.deleteHall(id);
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public ResponseVO updateHall(@PathVariable int id,@RequestBody HallFormVO hallFormVO){
        return hallService.changeHallInfo(id,hallFormVO);
    }
}
