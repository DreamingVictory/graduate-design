package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService service;
    @RequestMapping("queryAllCategory")
    public List<Category> queryAllCategory(HttpSession session){
        List<Category> categories = service.queryAllCategory();
        session.setAttribute("category",categories);
        return categories;
    }
}
