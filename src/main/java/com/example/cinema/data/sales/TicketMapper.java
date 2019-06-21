package com.example.cinema.data.sales;

import com.example.cinema.po.Order;
import com.example.cinema.po.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface TicketMapper {

    int insertTicket(Ticket ticket);

    int insertTickets(List<Ticket> tickets);

    //TODO:
    int insertOrder(Order order);

    //TODO:
    void insertTicketsInOrder(@Param("orderId")int orderId,@Param("ticketId") List<Integer>ticketIdList);

    void deleteTicket(int ticketId);

    void updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    //TODO:
    void updateOrderStatus(@Param("orderId")int orderId,@Param("status")int status);

    List<Ticket> selectTicketsBySchedule(int scheduleId);

    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    Ticket selectTicketById(int id);

    List<Ticket> selectTicketByUser(int userId);

    //TODO:
    List<Ticket> selectTicketByOrder(int orderId);

    void updateTicketingState(@Param("ticketId") int ticketId, @Param("ticketingState") int ticketingState);

    void updateUseCoupon(@Param("ticketId") int ticketId, @Param("useCoupon") int useCoupon);

    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredTicket();
}

