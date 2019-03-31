package com.baizhi.controller;

import com.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("insertOrder")
    public void insertOrder(Integer addressId, HttpSession session){
        orderService.insertOrder(addressId,session);
    }
}
