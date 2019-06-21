package com.example.cinema.blImpl.user;

import com.example.cinema.vo.VIPUserVO;

import java.util.List;

public interface AccountServiceForBl {
    boolean isExist(int userId);

    List<VIPUserVO> getVIPUserListBySpendAmount(double amount);
}
