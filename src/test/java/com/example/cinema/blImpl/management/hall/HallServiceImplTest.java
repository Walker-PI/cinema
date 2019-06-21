package com.example.cinema.blImpl.management.hall;

import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallFormVO;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.junit.Assert;
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
    public void searchHall() {
        ResponseVO responseVO =  hallService.searchHall(1);
        Hall hall = (Hall)responseVO.getContent();
        String hallString = "hallName:"+hall.getName()+" column:"+hall.getColumn()+" row:"+hall.getRow();
        String result = "hallName:test column:6 row:9";
        Assert.assertEquals(result, hallString);
    }

    @Test
    public void addHall() {
        HallFormVO hallFormVO = new HallFormVO();
        hallFormVO.setName("5D影厅");
        hallFormVO.setColumn(7);
        hallFormVO.setRow(10);

        ResponseVO responseVO = hallService.addHall(hallFormVO);
        Assert.assertEquals(true, responseVO.getSuccess());
    }
}