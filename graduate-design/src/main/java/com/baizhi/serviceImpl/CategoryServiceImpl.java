package com.baizhi.serviceImpl;

import com.baizhi.entity.Category;
import com.baizhi.mapper.CategoryMapper;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categories = categoryMapper.queryAllCategory();
            if(categories.isEmpty()){
            throw new RuntimeException("类别还没有添加，请添加后查询");
        }
        return categories;
    }
}
