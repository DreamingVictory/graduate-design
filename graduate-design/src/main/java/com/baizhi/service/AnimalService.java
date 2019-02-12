package com.baizhi.service;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Animal;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalService {
    public PageBeanDto<Animal> queryAllAnimal(Integer pageNo, Integer pageRows);
    /*public List<Animal> queryAllAnimal();*/
    public List<Animal> queryAnimalsByCommend();
    public List<Animal> queryByDiscount();
    public List<Animal> queryByCatDog();
    public Integer totalCount();
    public List<Animal> queryByWaterLand();
    public List<Animal> queryByBirdFish();
    public Animal queryOneById(Integer id);
    public List<Animal> queryByCount();

    public void insertAnimal(Animal animal, MultipartFile file);
    public void updateAnimal(Animal animal);

    public List<Animal> queryAllLucene(String params);
}
