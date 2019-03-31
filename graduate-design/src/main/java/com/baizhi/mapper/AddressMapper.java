package com.baizhi.mapper;

import com.baizhi.entity.Address;
import tk.mybatis.mapper.common.Mapper;

public interface AddressMapper extends Mapper<Address> {
    int addAddress(Address address);
}
