package com.baizhi.controller;

import com.baizhi.entity.Order;
import com.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("insertOrder")
    public void insertOrder(Integer addressId, HttpSession session){
        orderService.insertOrder(addressId,session);
    }

    @RequestMapping("queryAllOrders")
    public List<Order> queryAllOrders(){
        List<Order> orders = orderService.queryAllOrders();
        return orders;
    }

    @RequestMapping("delOrder")
    public String delOrder(Integer id){
        try {
            orderService.delOrder(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
