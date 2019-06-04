package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ApplicationTests {

    @Before
    public void init(){
        System.out.println("开始测试----------------------");
    }

    @After
    public void after(){
        System.out.println("结束测试----------------------");
    }


}