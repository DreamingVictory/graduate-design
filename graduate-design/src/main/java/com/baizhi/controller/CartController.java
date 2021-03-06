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
    public String addCart(Integer id,Integer count, HttpSession session){
        String result = service.addCart(id,count, session);
        return result;
    }

    @RequestMapping("getCart")
    public String getCartAnimal(HttpSession session){
        String result = service.getCartAnimal(session);
        return "forward:/html/shopcart.jsp";
    }

    @RequestMapping("getCart2")
    public String getCartAnimal2(HttpSession session){
        String result = service.getCartAnimal(session);
        return "forward:/html/order.jsp";
    }

    @RequestMapping("deleteCart")
    @ResponseBody
    public Double deleteCartItem(Integer id,HttpSession session){
        Double s = service.deleteCartItem(id, session);
        return s;
    }


    @RequestMapping("subCart")
    @ResponseBody
    public Double subCart(Integer id,HttpSession session){
        return service.subCart(id,session);
    }

    @RequestMapping("plusCart")
    @ResponseBody
    public Double plusCart(Integer id,HttpSession session){
        return service.addCart(id,session);
    }



}
