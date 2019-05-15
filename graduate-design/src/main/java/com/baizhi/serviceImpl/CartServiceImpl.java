package com.baizhi.serviceImpl;

import com.baizhi.entity.Animal;

import com.baizhi.entity.CartItem;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.service.CartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    AnimalMapper animalMapper;
    @Autowired
    RedisTemplate redisTemplate;


    @Override//添加购物车
    public String addCart(Integer id,Integer count, HttpSession session) {
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return "error";
        }
        //将购物车看作是一个Map，商品Id为键，购物车项为值
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        if(map==null){//购物车不存在
            Map<Integer, CartItem> map2=new HashMap<>();
            CartItem cartItem=new CartItem();
            Animal animal = animalMapper.selectByPrimaryKey(id);
            cartItem.setAnimal(animal);
            cartItem.setCount(count);
            cartItem.setTotalPrice(animal.getPrice()*count);
            map2.put(id,cartItem);

            strOps.set(principal,map2);
            strOps.set("totalPrice",animal.getPrice()*count);
            System.out.println(strOps.get("totalPrice"));
        }else{//购物车存在
            if(map.containsKey(id)){//该商品已存在
                CartItem cartItem = map.get(id);
                cartItem.setCount(cartItem.getCount()+count);
                cartItem.setTotalPrice(cartItem.getAnimal().getPrice()*cartItem.getCount());
                map.put(id,cartItem);

                Double totalPrice=(Double)strOps.get("totalPrice") + cartItem.getAnimal().getPrice()*cartItem.getCount();
                strOps.set(principal,map);
                strOps.set("totalPrice",totalPrice);
                System.out.println(strOps.get("totalPrice"));
            }else{//商品不存在
                CartItem cartItem=new CartItem();
                Animal animal = animalMapper.selectByPrimaryKey(id);
                cartItem.setAnimal(animal);
                cartItem.setCount(count);
                cartItem.setTotalPrice(animal.getPrice()*count);
                map.put(id,cartItem);

                Double totalPrice=(Double)strOps.get("totalPrice") + animal.getPrice()*count;
                strOps.set(principal,map);
                strOps.set("totalPrice",totalPrice);
                System.out.println(strOps.get("totalPrice"));
            }
        }

        return "ok";
    }

    @Override
    public String getCartAnimal(HttpSession session) {
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return "error";
        }
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        if(map != null && map.size() > 0){
            List<CartItem> animals=new ArrayList<>();
            Set<Integer> integers = map.keySet();
            for (Integer integer : integers) {
                animals.add(map.get(integer));
            }
            session.setAttribute("cartitem",animals);
            session.setAttribute("totalPrice",strOps.get("totalPrice"));
        }
        System.out.println(strOps.get("totalPrice"));
        return "ok";
    }

    @Override
    public void deleteCartItem(Integer id){
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return ;
        }
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        map.remove(id);
        CartItem cartItem = map.get(id);
        strOps.set("totalPrice",(Double)strOps.get("totalPrice")-cartItem.getTotalPrice());
        strOps.set(principal,map);
    }

    @Override
    public void batchDelete(List<Integer> ids){
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        if(principal==null){
            return ;
        }
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        for (Integer id : ids) {
            map.remove(id);
            CartItem cartItem = map.get(id);
            strOps.set("totalPrice",(Double)strOps.get("totalPrice")-cartItem.getTotalPrice());
        }
        strOps.set(principal,map);
    }

    @Override
    public void subCart(Integer id) {
        Animal animal = animalMapper.selectByPrimaryKey(id);
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        if(map != null && map.size() > 0){
            if(map.containsKey(id)){
                CartItem cartItem = map.get(id);
                if(cartItem.getCount()>1){
                    cartItem.setCount(cartItem.getCount()-1);
                    cartItem.setTotalPrice(cartItem.getTotalPrice()-animal.getPrice());
                    Double totalPrice = (Double) strOps.get("totalPrice") - animal.getPrice();
                    strOps.set("totalPrice",totalPrice);
                    System.out.println(totalPrice);
                }
                map.put(id,cartItem);
            }
        }
        strOps.set(principal,map);

    }

    @Override
    public void addCart(Integer id) {
        Animal animal = animalMapper.selectByPrimaryKey(id);
        ValueOperations strOps = redisTemplate.opsForValue();
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        Map<Integer, CartItem> map =(Map<Integer, CartItem>) strOps.get(principal);
        if(map != null && map.size() > 0){
            if(map.containsKey(id)){
                CartItem cartItem = map.get(id);
                cartItem.setCount(cartItem.getCount()+1);
                cartItem.setTotalPrice(cartItem.getTotalPrice()+animal.getPrice());
                map.put(id,cartItem);
            }
        }
        strOps.set(principal,map);
        Double totalPrice = (Double) strOps.get("totalPrice") + animal.getPrice();
        strOps.set("totalPrice",totalPrice);
        System.out.println(strOps.get("totalPrice"));
    }
}
