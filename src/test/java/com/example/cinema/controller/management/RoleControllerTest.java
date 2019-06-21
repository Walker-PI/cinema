package com.example.cinema.controller.management;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private RoleController roleController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }


    @Test
    public void deleteRole() {
    }

    @Test
    public void getAllManagerAndStaff() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/role/get/all")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "\"success\":true";
            Boolean answer = response.contains(result);
            Assert.assertEquals(true, answer);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByUsername() {
        try{

            String username = "AdminLuo";
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .post("/role/getByName")
                    .param("username", username)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "AdminLuo";
            Boolean answer = response.contains(result);
            Assert.assertEquals(true, answer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById1() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/role/get/{userId}", 12)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "用户不存在";
            Boolean answer = response.contains(result);
            Assert.assertEquals(true, answer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUserById2() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/role/get/{userId}", 34)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "AdminZhou";
            Boolean answer = response.contains(result);
            Assert.assertEquals(true, answer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserType() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .post("/role/update")
                    .param("userId", "31")
                    .param("userType", "1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "\"success\":true";
            Boolean answer = response.contains(result);
            Assert.assertEquals(true, answer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}