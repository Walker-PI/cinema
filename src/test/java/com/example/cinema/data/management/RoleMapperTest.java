package com.example.cinema.data.management;

import com.example.cinema.po.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleMapperTest {

    @Autowired
    RoleMapper roleMapper;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("kohaku");
        user.setPassword("kohakuTXDY");
        user.setUserType(2);
        user.setPhotoURL("https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=04212f723bc79f3d9becec62dbc8a674/a8ec8a13632762d03a1fce32a0ec08fa513dc61e.jpg");
        user.setEmail("hptxdy@163.com");
        roleMapper.insertUser(user);
    }

    @Test
    public void deleteUser() {
        roleMapper.deleteUser("testname");
    }

    @Test
    public void selectAllManagerAndStaff() {
        List<User>userList = roleMapper.selectAllManagerAndStaff();
        for(User user:userList){
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void selectUserByName() {
        //User user = roleMapper.selectUserByName("kohaku");
        //System.out.println(user.getId());
    }

    @Test
    public void selectUserByID() {
        User user = roleMapper.selectUserByID(17);
        System.out.println(user.getUsername());
    }

    @Test
    public void updateUserType() {
        roleMapper.updateUserType(17,1);
    }
}