package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    public void obtain(String phone);
    public void regist(User user,String code, String phone);
    public void login(String phone, String password,String vcode, HttpSession session);
}
