package com.baizhi.mapper;

import com.baizhi.entity.Animal;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnimalMapper extends Mapper<Animal> {
    public List<Animal> queryAnimalsByCommend();
    public List<Animal> queryByDiscount();
    public List<Animal> queryByCatDog();
    public List<Animal> queryByWaterLand();
    public List<Animal> queryByBirdFish();
    public List<Animal> queryByCount();/*热销推荐*/
}
