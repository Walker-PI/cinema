package com.example.cinema.vo;

import lombok.Data;

import java.util.List;

@Data
public class TicketHistoryVO {
    int historyId;
    String movieName;
    List<Integer>ticketIds;
}

