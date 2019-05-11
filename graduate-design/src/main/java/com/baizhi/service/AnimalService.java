package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Animal;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AnimalService {
    public PageBeanDto<Animal> queryAllAnimal();
    /*public List<Animal> queryAllAnimal();*/
    public List<Animal> queryAnimalsByCommend();
    public List<Animal> queryByDiscount();
    public List<Animal> queryByCatDog();
    public Integer totalCount();
    public List<Animal> queryByWaterLand();
    public List<Animal> queryByBirdFish();
    public Animal queryOneById(Integer id);
    public List<Animal> queryByCount();

    public List<Animal> queryAllAnimls(Integer id,HttpSession session);

    public void insertAnimal(Animal animal, MultipartFile file);
    public void updateAnimal(Animal animal);

    public List<Animal> queryAllLucene(String params,HttpSession session);
    List<Animal> queryFirstAnimals(Integer id,HttpSession session);

    //点击二级标题查询相对数据
    public List<Animal> queryAnimals(Integer id, HttpSession session,Integer pageNo,Integer pageRows);
    //点击所有商品
    public List<Animal> queryMoreAnimals(HttpSession session);
    //销量排序
    public List<Animal> orderBySaleCount(HttpSession session);
    //价格排序
    public List<Animal> orderByPrice(HttpSession session);
   /* //新品排序
    public List<Animal> orderByDate(HttpSession session);
    //折扣排序
    public List<Animal> orderByDiscount(HttpSession session);*/
}
