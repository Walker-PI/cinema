package com.example.cinema.vo;

import lombok.Data;
@Data
public class RoleForm {
    private String username;
    /**
     * 用户密码
     */
    private String password;

    private String email;

    private int userType;

}
