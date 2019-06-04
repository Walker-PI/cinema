package com.example.cinema.bl.management;

import com.example.cinema.vo.RefundStrgVO;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface RefundStrategyService {

    ResponseVO getAllStrategy();

    ResponseVO updateAllStrategy(List<RefundStrgVO> refundStrgVOList);
}
