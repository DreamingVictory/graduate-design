package com.baizhi.serviceImpl;

import com.baizhi.entity.*;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.mapper.OrderMapper;
import com.baizhi.mapper.OrderitemMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AnimalMapper animalMapper;
    @Autowired
    OrderitemMapper orderitemMapper;

    @Override
    public void insertOrder(Integer addressId, HttpSession session) {

        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        User user=new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);


        Order order=new Order();
        Double totalprice=(Double)strOps.get("totalPrice");
        order.setUserId(user1.getId());
        order.setTotalPrice(totalprice);
        order.setAddressId(addressId);
        orderMapper.insertOrder(order);
        //提交订单改变数据库中商品的销量
        //根据商品id查一个  得到商品的销量
        //提交订单时将每一个订单项的个数进行添加

        Set<Integer> keys = map.keySet();
        for (Integer k : keys) {
            Animal animal = animalMapper.selectByPrimaryKey(k);
            Integer count = animal.getCount();
            animal.setCount(count+map.get(k).getCount());
            animalMapper.selectCount(animal);
            Orderitem orderitem=new Orderitem();

            orderitem.setOrderId(order.getOrderId());
            orderitem.setAnimalId(k);
            CartItem cartItem = map.get(k);
            orderitem.setCount(cartItem.getCount());

            //根据商品id更改该商品的saleCount
            orderitem.setTotalPrice(cartItem.getTotalPrice());
            orderitemMapper.insert(orderitem);
        }
        //通过订单id查一个  显示订单成功界面
        Order order1 = orderMapper.selectByPrimaryKey(order.getOrderId());
        session.setAttribute("order1", order1);

    }
}
