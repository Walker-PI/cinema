package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)

public class AccountServiceImplTest {

    @Autowired
    AccountService accountService;
    @Test
    public void registerAccount() {
    }

    @Test
    public void login() {
        UserForm userForm = new UserForm();
        userForm.setUsername("hahaha");
        userForm.setPassword("123456");
        ResponseVO responseVO =accountService.login(userForm);
        UserVO userVO = (UserVO)responseVO.getContent();
        Assert.assertEquals(2, userVO.getUserType());
    }
}