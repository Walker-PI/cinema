package com.example.cinema.vo;

import lombok.Data;
@Data
public class CancelTicketForm {
    int scheduleId;
    int movieId;
    public CancelTicketForm(int scheduleId,int movieId){
        this.movieId=movieId;
        this.scheduleId = scheduleId;
    }
}

