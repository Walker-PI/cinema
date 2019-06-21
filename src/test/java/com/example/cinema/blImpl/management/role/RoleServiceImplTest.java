package com.example.cinema.blImpl.management.role;

import com.example.cinema.bl.management.RoleService;
import com.example.cinema.po.User;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleForm;
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
public class RoleServiceImplTest {
    @Autowired
    RoleService roleService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addRole() {
        RoleForm roleForm = new RoleForm();
        roleForm.setUsername("トゥーナ");
        roleForm.setPassword("TNtxdy");
        roleForm.setEmail("tuonatxdy@163.com");
        roleForm.setUserType(0);
        roleService.addRole(roleForm);
    }

    @Test
    public void deleteRoleByUsername() {
        roleService.deleteRoleByUsername("kohaku");
    }

    @Test
    public void searchAllManagerAndStaff() {
        ResponseVO responseVO = roleService.searchAllManagerAndStaff();
        List<User>userList = (List)responseVO.getContent();
        for(User user:userList){
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void searchUserByUserName() {
        ResponseVO responseVO = roleService.searchUserByUserName("test");
        List<User>userList = (List<User>) responseVO.getContent();
        for(User user:userList){
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void searchUserById() {
        ResponseVO responseVO = roleService.searchUserById(18);
        System.out.println(responseVO.getSuccess());
    }

    @Test
    public void changeUserType() {
        roleService.changeUserType(18,2);
    }
}