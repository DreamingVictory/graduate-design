package com.baizhi.service;

import javax.servlet.http.HttpSession;

public interface CartService {
    public void addCart(Integer id, HttpSession session);
}
