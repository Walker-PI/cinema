package com.example.cinema.data.user;

import com.example.cinema.vo.VIPUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void selectVIPUserBySpendAmount() {
        List<VIPUserVO>vipUserVOS = accountMapper.selectVIPUserBySpendAmount(700);
        for(VIPUserVO vipUserVO:vipUserVOS){
            System.out.println(vipUserVO.getId());
            System.out.println(vipUserVO.getName());
            System.out.println(vipUserVO.getSpend());
        }
    }
}