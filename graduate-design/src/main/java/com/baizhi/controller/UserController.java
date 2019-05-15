package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.entity.Consumer;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String logoutUser() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/html/index.jsp";
    }

    @RequestMapping("findByPhone")
    public String findByPhone(String phone,HttpSession session){
        User user = service.findByPhone(phone, session);
        return "redirect:/html/info.jsp";
    }

    @RequestMapping("update")
    public void update(User user){
        service.update(user);
    }

    @RequestMapping("statisCount")
    @ResponseBody
    public List<Integer> statisCount(String data1, String data2, String data3) {
        List<Integer> integers = service.statisCount(data1, data2, data3);

        return integers;
    }

    @RequestMapping("groupByProvince")
    @ResponseBody
    public List<Province> groupByProvince(String data1, String data2, String data3) {
        List<Province> list = service.groupByProvince();
        return list;
    }

    @RequestMapping("updateUser")
    public void updateUser(User user, String data1, String data2, String data3) {
        service.updateUser(user, data1, data2, data3);
    }

    @RequestMapping("exportUser")
    public void exportUser(HttpServletResponse response, HttpServletRequest request) {
        service.exportUser(response, request);
    }

    @RequestMapping("queryAllUser")
    @ResponseBody
    public PageBeanDto<User> queryAllUser(Integer page, Integer rows) {
        PageBeanDto<User> pb = service.queryAllUser(page, rows);
        return pb;
    }


    @RequestMapping("changePassword")
    @ResponseBody
    public String changePassword(String oldPassword, String newPassword,String newPassword2) {
        String s = service.changePassword(oldPassword, newPassword, newPassword2);
        return s;
    }
    @RequestMapping("getUsername")
    @ResponseBody
    public String getUsername() {
        return service.getUsername();
    }

}
