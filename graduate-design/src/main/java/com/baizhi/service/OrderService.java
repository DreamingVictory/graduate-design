package com.baizhi.service;

import javax.servlet.http.HttpSession;

public interface OrderService {
    public void insertOrder(Integer addressId, HttpSession session);
}
