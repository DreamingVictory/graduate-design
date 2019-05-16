package com.baizhi.controller;

import com.baizhi.dto.OrderDTO;
import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Address;
import com.baizhi.entity.Order;
import com.baizhi.entity.Role;
import com.baizhi.entity.User;
import com.baizhi.mapper.AddressMapper;
import com.baizhi.mapper.OrderMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.AddressService;
import com.baizhi.service.OrderService;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userService;
    @Autowired
    AddressMapper addressService;

    @RequestMapping("insertOrder")
    public Order insertOrder(Integer addressId, HttpSession session){
        return orderService.insertOrder(addressId,session);
    }

    @RequestMapping("queryAllOrders")
    public List<Order> queryAllOrders(){
        List<Order> orders = orderService.queryAllOrders();
        return orders;
    }

    @RequestMapping("delOrder")
    public String delOrder(Integer id){
        try {
            orderService.delOrder(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("queryAllOrder")
    public PageBeanDto<OrderDTO> queryAllOrder(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Order> orderList = orderMapper.selectAll();
        if (orderList.isEmpty()) {
            return new PageBeanDto<>();
        }
        List<OrderDTO> dtoList = orderList.stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(order, orderDTO);
            User user = userService.selectByPrimaryKey(order.getUserId());
            orderDTO.setUserName(user.getUsername());
            Address address = addressService.selectByPrimaryKey(order.getAddressId());
            orderDTO.setDetailAddress(address.getDetailAddress());
            return orderDTO;
        }).collect(Collectors.toList());


        PageBeanDto pb = new PageBeanDto();
        pb.setRows(dtoList);
        pb.setTotal(orderMapper.selectCount(null));
        return pb;
    }
}
