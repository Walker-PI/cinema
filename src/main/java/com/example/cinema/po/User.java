package com.example.cinema.po;

import com.example.cinema.vo.RoleForm;

/**
 * @author huwen
 * @date 2019/3/23
 */
public class User {
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像海报
     */
    private String photoURL;

    /**
     * 用户类型
     */
    private int userType;

    /**
     * 用户邮箱
     */
    private String email;

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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User roleForm2User(RoleForm roleForm){
        User user = new User();
        user.setEmail(roleForm.getEmail());
        user.setPassword(roleForm.getPassword());
        user.setUsername(roleForm.getUsername());
        user.setUserType(roleForm.getUserType());
        //设为默认图片的URL
        user.setPhotoURL("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2877354417,425406265&fm=27&gp=0.jpg");
        return user;
    }
}
