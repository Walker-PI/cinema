package com.example.cinema.controller.user;

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

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AccountController accountController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void login() {
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username","FanYe");
            jsonObject.put("password", "123456");
            String userForm = jsonObject.toString();

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userForm)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();

            String result = "true";
            Assert.assertEquals(result, response.substring(11, 15));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}