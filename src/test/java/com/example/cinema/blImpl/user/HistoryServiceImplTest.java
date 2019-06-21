package com.example.cinema.blImpl.user;

import com.example.cinema.vo.ChargeHistoryVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketHistoryVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryServiceImplTest {

    @Autowired
    private HistoryServiceImpl historyService;

    @Test
    public void getItemHistory1() {
        ResponseVO responseVO = historyService.getItemHistory(16, "charge");
        ChargeHistoryVO chargeHistoryVO = (ChargeHistoryVO) responseVO.getContent();
        double result = 100.0;
        Assert.assertEquals(result, chargeHistoryVO.getBalance(), 1);
    }

    @Test
    public void getItemHistory2() {
        ResponseVO responseVO = historyService.getItemHistory(12, "ticket");
        TicketHistoryVO ticketHistoryVO = (TicketHistoryVO) responseVO.getContent();
        int result = ticketHistoryVO.getTicketIds().get(0);
        Assert.assertEquals(5, result);
    }
}
