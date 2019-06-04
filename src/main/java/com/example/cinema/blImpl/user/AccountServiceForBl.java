package com.example.cinema.blImpl.user;

import com.example.cinema.vo.ResponseVO;
import org.springframework.stereotype.Service;


public interface AccountServiceForBl {
    public boolean isExist(int userId);
}
