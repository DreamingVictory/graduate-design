package com.baizhi.service;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    public String addCart(Integer id,Integer count, HttpSession session);
    public String getCartAnimal(HttpSession session);
    void deleteCartItem(Integer id);
    void batchDelete(List<Integer> ids);
    void subCart(Integer id);
    void addCart(Integer id);
}
