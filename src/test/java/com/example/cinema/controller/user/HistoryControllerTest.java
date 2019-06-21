package com.example.cinema.controller.user;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private HistoryController historyController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(historyController).build();
    }

    @Test
    public void getAllHistory(){
        try{
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/history/all/{userId}", 27)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "\"success\":true";
            Boolean answer = response.contains(result);
            Assert.assertEquals(answer, true);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void getItemHistory() {
        try{
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/history/item")
                    .param("historyId","40")
                    .param("type", "ticket")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "\"success\":true,\"message\":null,\"content\":{\"historyId\":40,\"movieName\":\"比悲伤更悲伤的故事\"";
            Boolean answer = response.contains(result);
            Assert.assertEquals(answer, true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}