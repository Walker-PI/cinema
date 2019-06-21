package com.example.cinema.controller.sales;

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
public class TicketControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TicketController ticketController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    public void getRefund() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/ticket/get/refund")
                    .param("ticketId","104")
                    .param("ticketId","105")
                    .param("ticketId","106")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            System.out.println(response);
            String result = "{\"success\":true,\"message\":null,\"content\":34.9}";
            Assert.assertEquals(result,response);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}