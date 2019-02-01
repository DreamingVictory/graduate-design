package com.baizhi.service;

import com.baizhi.entity.Animal;

import java.util.List;

public interface AnimalService {
    public List<Animal> queryAllAnimal();
    public List<Animal> queryAnimalsByCommend();
    public List<Animal> queryByDiscount();
    public List<Animal> queryByCatDog();
    public Integer totalCount();
    public List<Animal> queryByWaterLand();
    public List<Animal> queryByBirdFish();
    public Animal queryOneById(Integer id);
    public List<Animal> queryByCount();
}
