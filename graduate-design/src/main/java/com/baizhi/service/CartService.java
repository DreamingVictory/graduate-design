package com.baizhi.service;

import com.baizhi.entity.Animal;
import com.baizhi.entity.CartItem;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    public String addCart(Integer id, HttpSession session);
    public String getCartAnimal(HttpSession session);
}
