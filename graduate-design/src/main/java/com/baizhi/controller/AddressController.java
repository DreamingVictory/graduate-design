package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Address;
import com.baizhi.entity.Animal;
import com.baizhi.entity.User;
import com.baizhi.mapper.AddressMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.AddressService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    AddressService service;
    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @RequestMapping("queryHistoryAddress")
    public List<Address> queryHistoryAddress(){
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return Collections.emptyList();
        }
        User user=new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        List<Address> list = service.queryHistoryAddress(user1.getId());
        return list;
    }

    @RequestMapping("addAddress")
    public String addAddress(Address address){
        String s = service.addAddress(address);
        return s;
    }

    @RequestMapping("queryAddressById")
    public Address queryAddressById(String id){
        Address address = addressMapper.selectByPrimaryKey(id);
        return address;
    }

    @RequestMapping("updateAddress")
    public void updateAddress(Address address){
        addressMapper.updateByPrimaryKey(address);
    }

    @RequestMapping("queryAllAddress")
    public PageBeanDto<Address> queryAllAddress(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        PageBeanDto<Address> pageBeanDto = service.queryAllAddress();
        return pageBeanDto;
    }
}
