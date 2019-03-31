package com.baizhi.service;

import com.baizhi.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> queryHistoryAddress(Integer userId);
    String addAddress(Address address);
}
