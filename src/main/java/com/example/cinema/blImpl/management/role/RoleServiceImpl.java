package com.example.cinema.blImpl.management.role;

import com.example.cinema.bl.management.RoleService;
import com.example.cinema.data.management.RoleMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public ResponseVO addRole(RoleForm roleForm){
        try{
            User user = User.roleForm2User(roleForm);
            roleMapper.insertUser(user);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("用户名或邮箱已存在");
        }
    }


    public ResponseVO deleteRoleByUsername(String userName){
        try{
            roleMapper.deleteUser(userName);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("用户名不存在");
        }
    }


    public ResponseVO searchAllManagerAndStaff(){
        try{
            List<User>userList = roleMapper.selectAllManagerAndStaff();
            return ResponseVO.buildSuccess(userList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("搜索异常");
        }
    }


    public ResponseVO searchUserByUserName(String userName){
        try{
            List<User> userList = roleMapper.selectUserByName(userName);
            if(userList!=null)return ResponseVO.buildSuccess(userList);
            return ResponseVO.buildFailure("用户名不存在");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("搜索异常");
        }
    }


    public ResponseVO searchUserById(int userId){
        try{
            User user = roleMapper.selectUserByID(userId);
            if(user!=null)return ResponseVO.buildSuccess(user);
            return ResponseVO.buildFailure("用户不存在");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("搜索异常");
        }
    }


    public ResponseVO changeUserType(int userId,int userType){
        try{
            roleMapper.updateUserType(userId,userType);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("用户不存在");
        }
    }
}
