package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.vo.HallFormVO;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;
    @Autowired
    private ScheduleServiceForHallBl scheduleService;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    //根据ID查找影厅
    @Override
    public ResponseVO searchHall(int id){
        try {
            Hall hall = hallMapper.selectHallById(id);
            if(hall==null)return ResponseVO.buildFailure("无对应ID影厅");
            return ResponseVO.buildSuccess(hall);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    //添加影厅信息
    @Override
    public ResponseVO addHall(HallFormVO hallFormVO){
        try {
            String name = hallFormVO.getName();
            int column = hallFormVO.getColumn();
            int row = hallFormVO.getRow();
            Hall hall = new Hall();
            hall.setName(name);
            hall.setColumn(column);
            hall.setRow(row);
            hallMapper.insertHall(hall);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    //删除一个影厅信息
    @Override
    public ResponseVO deleteHall(int id){
        try {
            ResponseVO responseVO = this.searchHall(id);
            if(!responseVO.getSuccess())return ResponseVO.buildFailure("无对应ID的影厅");
            List<ScheduleItem>scheduleItemList = scheduleService.getScheduleByHallID(id);
            Date date = new Date();
            for(ScheduleItem scheduleItem:scheduleItemList){
                Date end = scheduleItem.getEndTime();
                if(end.compareTo(date)>=0)return ResponseVO.buildFailure("不能删除有排片的影厅");
            }
            hallMapper.deleteHall(id);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    //修改ID对应的影厅信息
    @Override
    public ResponseVO changeHallInfo(int id,HallFormVO hallFormVO){
        try {
            ResponseVO responseVO = this.searchHall(id);
            if(!responseVO.getSuccess())return ResponseVO.buildFailure("无对应ID的影厅");
            List<ScheduleItem>scheduleItemList = scheduleService.getScheduleByHallID(id);
            Date date = new Date();
            for(ScheduleItem scheduleItem:scheduleItemList){
                Date end = scheduleItem.getEndTime();
                if(end.compareTo(date)>=0)return ResponseVO.buildFailure("不能修改有排片的影厅");
            }
            String name = hallFormVO.getName();
            int column = hallFormVO.getColumn();
            int row = hallFormVO.getRow();
            hallMapper.updateHall(id,name,column,row);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private List<HallVO> hallList2HallVOList(List<Hall> hallList){
        List<HallVO> hallVOList = new ArrayList<>();
        for(Hall hall : hallList){
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }
}
