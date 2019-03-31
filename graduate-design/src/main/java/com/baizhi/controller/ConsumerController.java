package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.dto.Province;
import com.baizhi.entity.Consumer;
import com.baizhi.entity.User;
import com.baizhi.service.ConsumerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    ConsumerService service;

    @RequestMapping("statisCount")
    public List<Integer> statisCount(String data1, String data2, String data3) {
        List<Integer> integers = service.statisCount(data1, data2, data3);

        return integers;
    }

    @RequestMapping("groupByProvince")
    public List<Province> groupByProvince(String data1, String data2, String data3) {
        List<Province> list = service.groupByProvince();
        return list;
    }


    @RequestMapping("queryAllUser")
    public PageBeanDto<Consumer> queryAllUser(Integer page, Integer rows) {
        PageBeanDto<Consumer> pb = service.queryAllUser(page, rows);
        return pb;
    }

    @RequestMapping("updateUser")
    public void updateUser(Consumer user, String data1, String data2, String data3) {
        service.updateUser(user, data1, data2, data3);
    }

    @RequestMapping("exportUser")
    public void exportUser(HttpServletResponse response, HttpServletRequest request) {
        service.exportUser(response, request);
    }

    @RequestMapping("userImport")
    public void userImport() {
        service.userImport();
    }
}
