package com.baizhi.serviceImpl;

import com.baizhi.entity.*;
import com.baizhi.mapper.*;
import com.baizhi.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    private AddressMapper addressMapper;
    @Autowired
    OrderitemMapper orderitemMapper;

    @Override
    public Order insertOrder(Integer addressId, HttpSession session) {

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
            animalMapper.updateByPrimaryKeySelective(animal);
            Orderitem orderitem=new Orderitem();

            orderitem.setOrderId(order.getOrderId());
            orderitem.setAnimalId(k);
            CartItem cartItem = map.get(k);
            orderitem.setCount(cartItem.getCount());

            //根据商品id更改该商品的saleCount
            orderitem.setTotalPrice(cartItem.getTotalPrice());
            orderitemMapper.insertSelective(orderitem);
        }
        //通过订单id查一个  显示订单成功界面
        Order order1 = orderMapper.selectByPrimaryKey(order.getOrderId());
        session.setAttribute("order1", order1);


        //清空购物车
        redisTemplate.delete(principal);
        redisTemplate.delete("totalPrice");

        return order1;

    }

    @Override
    public List<Order> queryAllOrders() {
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setPhone(principal);
        User user1 = userMapper.selectOne(user);
        Order order = new Order();
        order.setUserId(user1.getId());
        List<Order> select = orderMapper.select(order);

        for (Order order1 : select) {
            Address address = addressMapper.selectByPrimaryKey(order1.getAddressId());
            order1.setAddress(address);
        }
        return select;
    }

    @Override
    public void delOrder(Integer id){
        orderMapper.deleteByPrimaryKey(id);

        Orderitem orderitem=new Orderitem();
        orderitem.setOrderId(id);
        List<Orderitem> list = orderitemMapper.select(orderitem);
        for (Orderitem orderitem1 : list) {
            orderitemMapper.delete(orderitem1);
        }
    }
}
