package com.refutrue.athena.bean;

import com.refutrue.athena.utils.base.BaseBean;
import com.refutrue.athena.utils.template.annotation.Ignore;
import com.refutrue.athena.utils.template.annotation.Title;

import java.io.Serializable;

@Title(value = "用户信息")
public class User extends BaseBean implements Serializable {

    private String userName;

    private String password;

    private String phone;

    @Ignore
    private static final long serialVersionUID = 1L;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}