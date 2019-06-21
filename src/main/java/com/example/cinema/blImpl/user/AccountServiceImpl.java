package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import com.example.cinema.vo.VIPUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Service
public class AccountServiceImpl implements AccountService,AccountServiceForBl {
    //TODO:如果实现必填邮箱功能后要把邮箱设置为NN和UN
    private final static String ACCOUNT_EXIST = "账号或邮箱已存在";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            String photoURL = "https://www.baidu.com/";
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword(), userForm.getEmail(), photoURL,2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getUsername());
        if (null == user) {
            return ResponseVO.buildFailure("用户名不存在");
        }
        else if(!user.getPassword().equals(userForm.getPassword())){
            return ResponseVO.buildFailure("用户密码错误");
        }
        return ResponseVO.buildSuccess(new UserVO(user));
    }


    @Override
    public boolean isExist(int userId){
        try {
            User user=accountMapper.getAccountById(userId);
            if(user==null)return false;
            else return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<VIPUserVO> getVIPUserListBySpendAmount(double amount){
        List<VIPUserVO>vipUserVOS
                =accountMapper.selectVIPUserBySpendAmount(amount);
        return vipUserVOS;
    }
}
