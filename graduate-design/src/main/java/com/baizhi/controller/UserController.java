package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("obtain")
    @ResponseBody
    public void obtain(String phone) {
        service.obtain(phone);
    }
    @RequestMapping("regist")
    public String regist(User user, String code, String phone){
        service.regist(user, code, phone);
        return "redirect:/html/login.jsp";
    }
    @RequestMapping("login")
    @ResponseBody
    public String login(String phone, String password,String vcode, HttpSession session){
        try {
            service.login(phone, password, vcode, session);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @RequestMapping("logoutUser")
    @ResponseBody
    public void logoutUser() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
