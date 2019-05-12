package com.baizhi.mapper;

import com.baizhi.entity.Category;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    public List<Category> queryAllCategory();
    public List<Integer> categoryChildren(Integer id);
}
