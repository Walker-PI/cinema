package com.example.cinema.bl.management;


import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleForm;

//TODO:mA

public interface RoleService {
    /**
     * 影院管理员添加一个新的角色
     * @param roleForm
     * @return
     */
    ResponseVO addRole(RoleForm roleForm);

    /**
     * 影院管理员删除一个新的角色
     * @param userName
     * @return
     */
    ResponseVO deleteRoleByUsername(String userName);

    /**
     * 影院管理员查询所有管理员和职工，先显示管理员再显示员工，按用户名顺序排序
     * @return
     */
    ResponseVO searchAllManagerAndStaff();

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    ResponseVO searchUserByUserName(String userName);

    /**
     * 根据用户Id查找用户
     * @param userId
     * @return
     */
    ResponseVO searchUserById(int userId);

    /**
     * 将对应user的用户类型进行更改
     * @param userId
     * @param userType
     * @return
     */
    ResponseVO changeUserType(int userId,int userType);

}
