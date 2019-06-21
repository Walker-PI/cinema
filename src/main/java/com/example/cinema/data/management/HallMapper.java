package com.example.cinema.data.management;

import com.example.cinema.po.Hall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 3:46 PM
 */
@Mapper
public interface HallMapper {
    /**
     * 查询所有影厅信息
     * @return
     */
    List<Hall> selectAllHall();

    /**
     * 根据id查询影厅
     * @return
     */
    Hall selectHallById(@Param("hallId") int hallId);

    /**
     * 添加一个新的影院
     * @param hall
     * @return
     */
    int insertHall(Hall hall);

    /**
     * 根据id删除影院
     * @param id
     * @return
     */
    int deleteHall(@Param("id")int id);

    /**
     * 更新影院信息
     * @param name
     * @param column
     * @param row
     */
    void updateHall(@Param("id")int id,@Param("name")String name,@Param("column")int column,@Param("row")int row);
}
