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

import javax.print.attribute.standard.Media;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest

public class HallControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private HallController hallController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(hallController).build();
    }


    @Test
    public void searchHallByID() {
        try{

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .get("/hall/{id}", 4)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "{\"success\":true,\"message\":null,\"content\":{\"id\":4,\"name\":\"4号厅\",\"column\":9,\"row\":7}}";
            Assert.assertEquals(result,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addHall() {
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","3D影厅");
            jsonObject.put("column","10");
            jsonObject.put("row", "12");
            String hallForm = jsonObject.toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .post("/hall/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hallForm)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "{\"success\":true,\"message\":null,\"content\":null}";
            Assert.assertEquals(result,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateHall() {
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","VIP影厅");
            jsonObject.put("column","10");
            jsonObject.put("row", "12");
            String hallForm = jsonObject.toString();
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .post("/hall/update/{id}", 4)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(hallForm)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String response = mvcResult.getResponse().getContentAsString();
            String result = "{\"success\":true,\"message\":null,\"content\":null}";
            Assert.assertEquals(result,response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}