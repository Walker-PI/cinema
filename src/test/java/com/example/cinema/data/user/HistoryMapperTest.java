package com.example.cinema.data.user;

import com.example.cinema.po.History;
import com.example.cinema.po.HistoryMovie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HistoryMapperTest {

    @Autowired
    HistoryMapper historyMapper;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectAllHistory() {
        List<History>historie = historyMapper.selectAllHistory(5);
        System.out.println(historie.size());
    }

    @Test
    public void selectChargeBalance() {
        double balance = historyMapper.selectChargeBalance(5);
        System.out.println(balance);
    }

    @Test
    public void selectMovieName() {
        List<HistoryMovie>historyMovies = historyMapper.selectTicket(47);
        HistoryMovie historyMovie = historyMovies.get(0);
        System.out.println(historyMovie.getTicketId());
    }

    @Test
    public void insertHistory() {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        History ticketHistory = new History(0,timestamp,100,"ticket",5);
        History chargeHistory = new History(0,timestamp,200,"charge",5);
        History cardHistory = new History(0,timestamp,50,"card",5);
        historyMapper.insertHistory(ticketHistory);
        historyMapper.insertHistoryMovie(ticketHistory.getId(),"比快乐更快乐的故事",7);
        historyMapper.insertHistory(chargeHistory);
        historyMapper.insertHistoryCharge(chargeHistory.getId(),50000);
        historyMapper.insertHistory(cardHistory);
    }
}