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
}
