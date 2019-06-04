package com.example.cinema.data.statistics;

import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.Hall;
import com.example.cinema.po.SimpleScheduleItem;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询date日期每部电影的排片次数
     * @param date
     * @return
     */
    List<MovieScheduleTime> selectMovieScheduleTimes(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的总票房（包括已经下架的，降序排列）
     * @return
     */
    List<MovieTotalBoxOffice> selectMovieTotalBoxOffice();

    /**
     * 查询在指定时间区间内所有电影的总票房（降序排列）
     * @return
     */
    List<MovieTotalBoxOffice> selectMovieTotalBoxOfficeBetweenStartDateAndEndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询某天每个客户的购票金额
     * @param date
     * @param nextDate
     * @return
     */
    List<AudiencePrice> selectAudiencePrice(@Param("date") Date date, @Param("nextDate") Date nextDate);


    /**
     * 查询ticket.schedule_id=scheduleId并且state = 1(订单成功)的订单数目
     * @param scheduleId
     * @return
     */
    int selectTicketNumByScheduleId(@Param("scheduleId") int scheduleId);

    /**
     * 查询hall.id=hallId的影厅
     * @param hallId
     * @return
     */
    Hall selectHallByHallId(@Param("hallId") int hallId);

    /**
     * 查询特定日期的排片信息（按电影的ID）排序
     * @param requireDate
     * @param nextDate
     * @return
     */
    List<SimpleScheduleItem> selectSimpleScheduleItemByDate(@Param("requireDate") Date requireDate, @Param("nextDate") Date nextDate);

}
