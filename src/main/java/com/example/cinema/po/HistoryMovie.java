package com.example.cinema.po;
import lombok.Data;

import java.util.List;

@Data
public class HistoryMovie {

    /**
     * 历史记录id
     */
    int historyId;

    /**
     * 电影名
     */
    String movieName;

    /**
     * 电影票id
     */
    int ticketId;
}
