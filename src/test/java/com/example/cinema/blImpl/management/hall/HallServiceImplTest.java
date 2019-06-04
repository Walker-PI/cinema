package com.example.cinema.blImpl.management.hall;

import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cinema.blImpl.management.hall.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HallServiceImplTest {


    @Autowired
    HallServiceImpl hallService;

    @Test
    public void searchAllHall() {
        //要求的
        List<HallVO>requiredList = new ArrayList<>();
        Hall hall1 = new Hall();
        hall1.setId(1);
        hall1.setName("1号厅");
        hall1.setColumn(10);
        hall1.setRow(5);
        HallVO hallVO1 = new HallVO(hall1);
        Hall hall2 = new Hall();
        hall1.setId(2);
        hall1.setName("2号厅");
        hall1.setColumn(12);
        hall1.setRow(8);
        HallVO hallVO2 = new HallVO(hall2);
        requiredList.add(hallVO1);
        requiredList.add(hallVO2);
        //测试获得的
        ResponseVO responseVO = hallService.searchAllHall();
        Object object = responseVO.getContent();
        List<HallVO>actualList = (List)responseVO.getContent();
        assertArrayEquals(requiredList.toArray(),actualList.toArray());
    }

    @Test
    public void searchHall() {
            System.out.println(hallService);
    }

    @Test
    public void addHall() {
    }

    @Test
    public void deleteHall() {
    }

    @Test
    public void changeHallInfo() {
    }

    @Test
    public void getHallById() {
    }
}