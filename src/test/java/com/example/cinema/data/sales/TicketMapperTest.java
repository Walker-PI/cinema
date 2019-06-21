package com.example.cinema.data.sales;

import com.example.cinema.po.Order;
import com.example.cinema.po.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketMapperTest {

    @Autowired
    TicketMapper ticketMapper;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertTicket() {
        Ticket ticket = new Ticket();
        ticket.setScheduleId(19);
        ticket.setTicketingState(0);
        ticket.setState(0);
        ticket.setRowIndex(2);
        ticket.setColumnIndex(5);
        ticket.setTime(new Timestamp(new Date().getTime()));
        ticket.setUserId(4);
        int id=ticketMapper.insertTicket(ticket);
        System.out.println(id);
    }

    @Test
    public void insertTickets() {
    }

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setName("电影票");
        order.setContent("电影票一张");
        order.setPrice(40);
        order.setStatus(0);
        ticketMapper.insertOrder(order);
        int id = order.getId();
        System.out.println(id);
    }

    @Test
    public void insertTicketsInOrder() {
        List<Ticket>tickets = ticketMapper.selectTicketByUser(16);
        List<Integer>ticketIntegerList = new ArrayList<>();
        for(Ticket ticket:tickets){
            int id = ticket.getId();
            ticketIntegerList.add(id);
        }
        ticketMapper.insertTicketsInOrder(3,ticketIntegerList);
    }


    @Test
    public void deleteTicket() {
    }

    @Test
    public void updateTicketState() {
    }

    @Test
    public void updateOrderStatus() {
        ticketMapper.updateOrderStatus(4,1);
    }

    @Test
    public void selectTicketsBySchedule() {
    }

    @Test
    public void selectTicketByScheduleIdAndSeat() {
    }

    @Test
    public void selectTicketById() {
    }

    @Test
    public void selectTicketByUser() {
    }

    @Test
    public void selectTicketByOrder() {
        List<Ticket>tickets = ticketMapper.selectTicketByOrder(3);
        for(Ticket ticket:tickets){
            System.out.println(ticket.getId());
        }
    }

    @Test
    public void cleanExpiredTicket() {
    }
}