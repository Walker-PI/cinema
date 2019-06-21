package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.HistoryService;
import com.example.cinema.data.user.HistoryMapper;
import com.example.cinema.po.History;
import com.example.cinema.po.HistoryMovie;
import com.example.cinema.vo.ChargeHistoryVO;
import com.example.cinema.vo.HistoryVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService,HistoryServiceForBl {
    @Autowired
    HistoryMapper historyMapper;

    @Override
    public ResponseVO getAllHistory(int userId){
        try{
            List<History>histories = historyMapper.selectAllHistory(userId);
            List<HistoryVO>historyVOS = this.histories2HistoryVOS(histories);
            return ResponseVO.buildSuccess(historyVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public ResponseVO getItemHistory(int historyId,String type){
        try{
            if(type.equals("ticket")){
                //买票的多返回一个movieName
                TicketHistoryVO ticketHistoryVO = new TicketHistoryVO();
                List<HistoryMovie>historyMovies = historyMapper.selectTicket(historyId);
                String movieName = historyMovies.get(0).getMovieName();
                List<Integer>ticketIds = new ArrayList<>();
                for(HistoryMovie movie:historyMovies){
                    ticketIds.add(movie.getTicketId());
                }
                ticketHistoryVO.setHistoryId(historyId);
                ticketHistoryVO.setMovieName(movieName);
                ticketHistoryVO.setTicketIds(ticketIds);
                return ResponseVO.buildSuccess(ticketHistoryVO);
            }
            else if(type.equals("charge")){
                //充钱的多返回一个卡余额：是指充完这笔订单后的卡余额，不是当前
                ChargeHistoryVO chargeHistoryVO = new ChargeHistoryVO();
                double balance = historyMapper.selectChargeBalance(historyId);
                chargeHistoryVO.setHistoryId(historyId);
                chargeHistoryVO.setBalance(balance);
                return ResponseVO.buildSuccess(chargeHistoryVO);
            }
            else if(type.equals("card")){
                //card时没有多余信息
                return ResponseVO.buildSuccess();
            }
            else{
                return ResponseVO.buildFailure("参数有误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("错误");
        }
    }

    @Override
    public boolean insertChargeHistory(History history,double balance){
        try{
            historyMapper.insertHistory(history);
            int historyId = history.getId();
            historyMapper.insertHistoryCharge(historyId,balance);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertTicketHistory(History history,String movieName,List<Integer>ticketIds){
        try{
            historyMapper.insertHistory(history);
            int historyId = history.getId();
            for(int ticketId:ticketIds){
                historyMapper.insertHistoryMovie(historyId,movieName,ticketId);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertCardHistory(History history){
        try{
            historyMapper.insertHistory(history);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private List<HistoryVO>histories2HistoryVOS(List<History>histories){
        List<HistoryVO>historyVOS = new ArrayList<>();
        for(History history:histories){
            HistoryVO historyVO = new HistoryVO(history);
            historyVOS.add(historyVO);
        }
        return historyVOS;
    }
}

