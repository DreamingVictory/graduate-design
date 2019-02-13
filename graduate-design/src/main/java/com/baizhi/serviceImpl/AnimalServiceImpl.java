package com.baizhi.serviceImpl;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Animal;
import com.baizhi.lucene.LuceneDao;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.service.AnimalService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalMapper animalMapper;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    LuceneDao luceneDao;


   @Override
    public PageBeanDto<Animal> queryAllAnimal(Integer pageNo, Integer pageRows) {
        List<Animal> animals = animalMapper.selectAll();
        int count = animalMapper.selectCount(null);
        PageBeanDto<Animal> pageBeanDto=new PageBeanDto<>();
        pageBeanDto.setRows(animals);
        pageBeanDto.setTotal(count);
        return pageBeanDto;
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

    @Override
    public void insertAnimal(Animal animal, MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            String fullPath = storePath.getFullPath();
            animal.setImg(fullPath);
            animalMapper.insertSelective(animal);
            luceneDao.createIndexToAnimal(animal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnimal(Animal animal) {
        animalMapper.updateByPrimaryKey(animal);
    }

    @Override
    public List<Animal> queryAllLucene(String params,HttpSession session) {
        List<Animal> animals = luceneDao.queryAllLucene(params);
        int count = animals.size();
        session.setAttribute("animals",animals);
        session.setAttribute("count",count);
        return animals;
    }

    @Override
    public List<Animal> queryAnimals(Integer id, HttpSession session) {
        Animal animal=new Animal();
        animal.setCategoryId(id);
        List<Animal> animals = animalMapper.select(animal);
        int count = animalMapper.selectCount(animal);
        session.setAttribute("animals",animals);
        session.setAttribute("count",count);

        return animals;
    }

    @Override
    public List<Animal> queryMoreAnimals(HttpSession session) {
        List<Animal> animals = animalMapper.selectAll();
        int count = animals.size();
        session.setAttribute("animals",animals);
        session.setAttribute("count",count);
        return animals;
    }

    @Override
    public List<Animal> orderBySaleCount(HttpSession session) {
        List<Animal> animals = (List<Animal>)session.getAttribute("animals");
        //得到每个动物的销量进行排序   默认降序

        for (int i=0;i<animals.size();i++) {
            Collections.sort(animals,new Comparator<Animal>(){


                @Override
                public int compare(Animal o1, Animal o2) {
                    if(o1.getCount()>=o2.getCount()){
                        return -1;
                    }else{
                        return 1;
                    }

                }
            });
        }
        return animals;

    }
}
