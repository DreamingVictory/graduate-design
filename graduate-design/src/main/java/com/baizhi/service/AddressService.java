package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Address;
import com.baizhi.entity.Animal;

import java.util.List;

public interface AddressService {
    public List<Address> queryHistoryAddress(Integer userId);
    String addAddress(Address address);
    PageBeanDto<Address> queryAllAddress();
}
