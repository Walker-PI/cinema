package com.example.cinema.data.management;


import com.example.cinema.po.RefundStrg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RefundStrategyMapper {

    /**
     * 得到所有的退票策略
     * @return
     */
    List<RefundStrg> selectAllStrategy();

    /**
     * 清空表内所有内容
     */
    void deleteAllStratefy();

    /**
     * 更新所有的退票策略
     * @param refundStrg
     * @return
     */
    void insertStrategy(RefundStrg refundStrg);

}
