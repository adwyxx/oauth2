package com.adwyxx.oauth.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 2447001179998212205L;

    private Integer id;

    private String userName;

    private String password;

    private String displayName;

    private String email;

    private String mobile;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User(User user)
    {
        super();
        this.id=user.getId();
        this.userName=user.getUserName();
        this.password=user.getPassword();
        this.displayName=user.getDisplayName();
        this.email=user.getEmail();
        this.mobile=user.getMobile();
        this.createTime=user.getCreateTime();
    }
}