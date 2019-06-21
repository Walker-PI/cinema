package com.example.cinema.bl.user;

import com.example.cinema.vo.ResponseVO;

public interface HistoryService {

    /**
     * 通过userId获取他所有的历史记录
     * @param userId
     * @return
     */
    ResponseVO getAllHistory(int userId);

    /**
     * 获取某一项具体的记录
     * @param historyId
     * @param type
     * @return
     */
    ResponseVO getItemHistory(int historyId,String type);

}

