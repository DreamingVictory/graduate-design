package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.entity.Consumer;
import com.baizhi.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
     void obtain(String phone);

     void regist(User user,String code, String phone);

     void login(String phone, String password,String vcode, HttpSession session);

     User findByPhone(String phone,HttpSession session);

     void update(User user);

     void updateUser(User user, String data1, String data2, String data3);

     void exportUser(HttpServletResponse response, HttpServletRequest request);

     List<Province> groupByProvince();

     List<Integer> statisCount(String data1, String data2, String data3);

     PageBeanDto<User> queryAllUser(Integer page, Integer rows);

     String changePassword(String oldPassword, String newPassword,String newPassword2);
     String getUsername();


}
