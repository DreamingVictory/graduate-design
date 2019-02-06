package com.baizhi.serviceImpl;

import com.baizhi.entity.Menu;
import com.baizhi.mapper.MenuMapper;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenu() {
        List<Menu> menus = menuMapper.queryAllMenu();
        if(menus.isEmpty()){
            throw  new RuntimeException("类别不存在，请添加");
        }
        return menus;
    }
}
