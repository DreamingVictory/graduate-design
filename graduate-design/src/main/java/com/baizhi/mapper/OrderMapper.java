package com.baizhi.mapper;

import com.baizhi.entity.Order;
import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order> {
    public void insertOrder(Order order);
}
