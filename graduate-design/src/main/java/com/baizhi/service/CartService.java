package com.baizhi.service;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    public String addCart(Integer id,Integer count, HttpSession session);
    public String getCartAnimal(HttpSession session);
    Double deleteCartItem(Integer id,HttpSession session);
    void batchDelete(List<Integer> ids);
    Double subCart(Integer id,HttpSession session);
    Double addCart(Integer id,HttpSession session);
}
