package com.example.cinema.controller.promotion;

import org.json.JSONArray;
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

public class VIPStrategyControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private VIPStrategyController vipStrategyController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(vipStrategyController).build();
    }


    @Test
    public void getAllStrategy() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/vipStrategy/get")
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