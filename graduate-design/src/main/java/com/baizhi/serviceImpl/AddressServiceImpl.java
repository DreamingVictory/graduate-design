package com.baizhi.serviceImpl;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Address;
import com.baizhi.entity.Animal;
import com.baizhi.entity.User;
import com.baizhi.mapper.AddressMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.AddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Address> queryHistoryAddress(Integer userId) {
        Address address=new Address();
        address.setUserId(userId);
        List<Address> addressList = addressMapper.select(address);
        if(addressList.isEmpty()){
            return Collections.emptyList();
        }
        return addressList;

    }

    @Override
    public String addAddress(Address address) {
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return "error";
        }
        User user=new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);

        address.setUserId(user1.getId());
        addressMapper.addAddress(address);
        return address.getId().toString();
    }

    @Override
    public PageBeanDto<Address> queryAllAddress(){
        List<Address> addresses = addressMapper.selectAll();
        int count = addressMapper.selectCount(null);
        PageBeanDto<Address> pageBeanDto=new PageBeanDto<>();
        pageBeanDto.setRows(addresses);
        pageBeanDto.setTotal(count);
        return pageBeanDto;

    }
}
