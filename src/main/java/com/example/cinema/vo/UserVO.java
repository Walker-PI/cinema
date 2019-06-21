package com.example.cinema.vo;

import com.example.cinema.po.User;

/**
 * @author fjj
 * @date 2019/4/11 3:22 PM
 */
public class UserVO {
    private Integer id;
    private String username;
    private String password;

    private String photoURL;

    private int userType;

    private String email;



    public UserVO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
