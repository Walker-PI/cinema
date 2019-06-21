package com.example.cinema.data.user;

import com.example.cinema.po.History;
import com.example.cinema.po.HistoryMovie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {

    /**
     * 根据用户ID查找所有他的支付记录
     * @param userId
     * @return
     */
    List<History> selectAllHistory(int userId);

    double selectChargeBalance(int historyId);

    List<HistoryMovie> selectTicket(int historyId);

    int insertHistory(History history);

    int insertHistoryCharge(@Param("historyId")int historyId,@Param("balance")double balance);

    int insertHistoryMovie(@Param("historyId")int historyId,@Param("movieName") String movieName,@Param("ticketId")int ticketId);

}
