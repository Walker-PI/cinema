package com.example.cinema.data.user;

import com.example.cinema.po.User;
import com.example.cinema.vo.VIPUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     * @param username
     * @param password
     * @return
     */
    int createNewAccount(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("photoURL") String photoURL,@Param("userType")int userType);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    User getAccountByName(@Param("username") String username);

    /**
     * 根据用户id查找账号
     * @param userid
     * @return
     */
    User getAccountById(@Param("userid") int userid);

    List<VIPUserVO>selectVIPUserBySpendAmount(double amount);
}
