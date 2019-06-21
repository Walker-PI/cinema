package com.example.cinema.blImpl.management.hall;

import com.example.cinema.po.ScheduleItem;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/28 12:30 AM
 */
public interface ScheduleServiceForHallBl {


    /**
     * 根据hall_id查找排片
     * @param hall_id
     * @return
     */
    List<ScheduleItem> getScheduleByHallID(int hall_id);

}
