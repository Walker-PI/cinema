package com.example.cinema.blImpl.user;

import com.example.cinema.po.History;

import java.util.List;

public interface HistoryServiceForBl {

    /**
     * 添加充值的历史记录
     * @param history
     * @param balance
     * @return
     */
    boolean insertChargeHistory(History history,double balance);

    /**
     * 添加购票的历史记录
     * @param history
     * @param movieName
     * @return
     */
    boolean insertTicketHistory(History history, String movieName, List<Integer>ticketIds);

    /**
     * 添加买卡的历史记录
     * @param history
     * @return
     */
    boolean insertCardHistory(History history);
}

