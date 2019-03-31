package com.baizhi.controller;

import com.baizhi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService service;
    @ResponseBody
    @RequestMapping("addCart")
    public String addCart(Integer id, HttpSession session){
        String result = service.addCart(id, session);
        return result;
    }

    @RequestMapping("getCart")
    public String getCartAnimal(HttpSession session){
        String result = service.getCartAnimal(session);
        return "forward:/html/shopcart.jsp";
    }

}
