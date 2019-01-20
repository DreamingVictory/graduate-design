package com.baizhi.mapper;

import com.baizhi.entity.Authority;
import com.baizhi.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    public List<Role> queryAllRoleByPhone(String phone);
    public List<Authority> queryAllAuthorityByRole(String roleName);
}
