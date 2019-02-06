package com.baizhi.controller;

import com.baizhi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService service;

    @RequestMapping("addCart")
    public void addCart(Integer id, HttpSession session){

    }
}
