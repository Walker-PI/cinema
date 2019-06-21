package com.example.cinema.blImpl.management.refundStrategy;

import com.example.cinema.bl.management.RefundStrategyService;
import com.example.cinema.data.management.RefundStrategyMapper;
import com.example.cinema.po.RefundStrg;
import com.example.cinema.vo.RefundStrgVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefundStrategyServiceImpl implements RefundStrategyService, RefundStrategyServiceForBl{
    @Autowired
    RefundStrategyMapper refundStrategyMapper;

    @Override
    public ResponseVO getAllStrategy() {
        try {
            return ResponseVO.buildSuccess(refundStrgToRefundStrgVO(refundStrategyMapper.selectAllStrategy()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateAllStrategy(List<RefundStrgVO> refundStrgVOList) {
        try {
            refundStrategyMapper.deleteAllStratefy();
            for(RefundStrgVO refundStrgVO : refundStrgVOList){
                RefundStrg refundStrg =  new RefundStrg(refundStrgVO);
                refundStrategyMapper.insertStrategy(refundStrg);
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<RefundStrg> getAllRefundStrategy() {
        return refundStrategyMapper.selectAllStrategy();
    }

    private List<RefundStrgVO> refundStrgToRefundStrgVO(List<RefundStrg> refundStrgList){
        //PO转VO
        List<RefundStrgVO> refundStrgVOList = new ArrayList<>();
        for(RefundStrg refundStrg : refundStrgList){
            refundStrgVOList.add(new RefundStrgVO(refundStrg));
        }
        return refundStrgVOList;
    }
}
