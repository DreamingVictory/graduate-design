package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CategoryService {
    public List<Category> queryAllCategory();
    public PageBeanDto<Category> queryAllCategory(Integer page, Integer rows);
    public void insertCategory(Category category);
    public void exportCategory(HttpServletResponse response, HttpServletRequest request);
}
