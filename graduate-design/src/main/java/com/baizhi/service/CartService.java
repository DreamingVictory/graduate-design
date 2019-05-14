package com.baizhi.service;

import javax.servlet.http.HttpSession;

public interface CartService {
    public String addCart(Integer id,Integer count, HttpSession session);
    public String getCartAnimal(HttpSession session);
    void deleteCartItem(Integer id);
}
