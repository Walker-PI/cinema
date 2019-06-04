package com.example.cinema.bl.management;

import com.example.cinema.vo.HallFormVO;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();

    /**
     * 根据ID查找影厅
     * @param id
     * @return
     */
    ResponseVO searchHall(int id);

    /**
     * 录入影厅信息
     * @param hallFormVO
     * @return
     */
    ResponseVO addHall(HallFormVO hallFormVO);

    /**
     * 删除一个影厅
     * @param id
     * @return
     */
    ResponseVO deleteHall(int id);


    /**
     * 根据ID选择影厅，修改其信息
     * @param id
     * @param hallFormVO
     * @return
     */
    ResponseVO changeHallInfo(int id,HallFormVO hallFormVO);
}
