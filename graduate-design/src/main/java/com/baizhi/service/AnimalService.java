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

    public Integer totalPage(Integer id);

    //一共找到的动物数量
    Integer findAnimalCount(Integer id);

    public void insertAnimal(Animal animal, MultipartFile file);
    public void updateAnimal(Animal animal);

    public List<Animal> queryAllLucene(String params,HttpSession session);

    //点击所有商品
    public List<Animal> queryMoreAnimals(HttpSession session);
    //销量排序
    public List<Animal> orderBySaleCount(Integer id,Integer status);
    //价格排序
    public List<Animal> orderByPrice(Integer id,Integer status);
    //新品排序
    public List<Animal> orderByDate(Integer id,Integer status);

    //猜你喜欢
    public List<Animal> likeAnimal();
}
