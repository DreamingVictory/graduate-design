package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Role;
import com.baizhi.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController  {

    @Autowired
    RoleMapper roleMapper;

    @RequestMapping("queryAllRole")
    public PageBeanDto<Role> queryAllUser(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Role> roles = roleMapper.selectAll();
        if (roles.isEmpty()) {
            throw new RuntimeException("角色列表不存在...");
        }
        PageBeanDto pb = new PageBeanDto();
        pb.setRows(roles);
        pb.setTotal(roleMapper.selectCount(null));
        return pb;

    }
}
