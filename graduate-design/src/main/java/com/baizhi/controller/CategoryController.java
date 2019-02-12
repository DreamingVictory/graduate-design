package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService service;
    @RequestMapping("queryAllCategory")
    public List<Category> queryAllCategory(){

        List<Category> categories = service.queryAllCategory();
        return categories;
    }
   /* public PageBeanDto<Category> queryAllCategory(Integer page,Integer rows){
        PageHelper.startPage(page,rows);
        PageBeanDto<Category> pageBeanDto= service.queryAllCategory(page,rows);
        return pageBeanDto;
    }*/
    @RequestMapping("queryAllSecondCategory")
    public List<Category> queryAllSecondCategory(){
        List<Category> categories = service.queryAllCategory();
        List<Category> categories1 = new ArrayList<>();

        for (Category category : categories) {
           for(int i=0;i<category.getChildren().size();i++){
               if(category.getChildren().get(i).getParentId()!=null){
                   categories1.add(category.getChildren().get(i));
               }

           }

        }
        return categories1;
    }
    @RequestMapping("insertCategory")
    public void insertCategory(Category category) {
        service.insertCategory(category);
    }
}
