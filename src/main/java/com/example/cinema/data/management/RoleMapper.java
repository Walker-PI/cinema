package com.example.cinema.data.management;

import com.example.cinema.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 插入一个新的用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 通过用户名删除用户
     * @param username
     */
    void deleteUser(String username);

    /**
     * 查找所有管理员和员工的账号
     * @return
     */
    List<User>selectAllManagerAndStaff();

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    List<User> selectUserByName(String username);

    /**
     * 通过用户的ID查找用户
     * @param userId
     * @return
     */
    User selectUserByID(int userId);

    /**
     * 更改用户类型
     * @param userId
     * @param userType
     */
    void updateUserType(@Param("userId") int userId,@Param("userType") int userType);
}
