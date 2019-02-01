package com.baizhi.serviceImpl;

import com.baizhi.entity.Animal;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalMapper animalMapper;

    @Override
    public List<Animal> queryAllAnimal() {
        List<Animal> animals = animalMapper.selectAll();
        return animals;
    }

    @Override
    public List<Animal> queryAnimalsByCommend() {
        List<Animal> animals = animalMapper.queryAnimalsByCommend();
        if(animals.isEmpty()){
            throw new RuntimeException("没有根据推荐度查询到小动物哎，记得添加");
        }
        List<Animal> list=new ArrayList<>();
        for (Animal animal : animals) {
            list.add(animal);
            if(list.size()==10){
                break;
            }
        }
        return list;
    }

    @Override
    public List<Animal> queryByDiscount() {
        List<Animal> animals = animalMapper.queryByDiscount();
        if(animals.isEmpty()){
            throw new RuntimeException("没有折扣商品o");
        }
        List<Animal> list=new ArrayList<>();
        for (Animal animal : animals) {
            list.add(animal);
            if(list.size()==12){
                break;
            }
        }
        return list;
    }

    @Override
    public List<Animal> queryByCatDog() {
        List<Animal> animals = animalMapper.queryByCatDog();

        List<Animal> list=new ArrayList<>();
        for (Animal animal : animals) {

            list.add(animal);
            if(list.size()==5){
                break;
            }
        }
        return list;
    }

    @Override
    public Integer totalCount() {
        int count = animalMapper.selectCount(null);
        return count;
    }

    @Override
    public List<Animal> queryByWaterLand() {
        List<Animal> animals = animalMapper.queryByWaterLand();
        List<Animal> list=new ArrayList<>();
        for (Animal animal : animals) {

            list.add(animal);
            if(list.size()==5){
                break;
            }
        }
        return list;
    }

    @Override
    public List<Animal> queryByBirdFish() {
        List<Animal> animals = animalMapper.queryByBirdFish();
        List<Animal> list=new ArrayList<>();
        for (Animal animal : animals) {

            list.add(animal);
            if(list.size()==5){
                break;
            }
        }
        return list;
    }

    @Override
    public Animal queryOneById(Integer id) {
        Animal animal = animalMapper.selectByPrimaryKey(id);
        return animal;
    }

    @Override
    /*销量*/
    public List<Animal> queryByCount() {
        List<Animal> animals = animalMapper.queryByCount();
        return animals;
    }
}
