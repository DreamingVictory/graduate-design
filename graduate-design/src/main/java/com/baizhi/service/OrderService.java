package com.baizhi.service;

import com.baizhi.entity.Order;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
    void insertOrder(Integer addressId, HttpSession session);

    List<Order> queryAllOrders();

    void delOrder(Integer id);
}
