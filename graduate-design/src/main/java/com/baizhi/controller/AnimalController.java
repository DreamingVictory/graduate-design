package com.baizhi.controller;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Animal;
import com.baizhi.service.AnimalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    AnimalService service;
    @RequestMapping("queryAllAnimal")
    @ResponseBody
    public PageBeanDto<Animal> queryAllAnimal(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        PageBeanDto<Animal> pageBeanDto = service.queryAllAnimal();
        return pageBeanDto;
    }

    @RequestMapping("queryAnimalsByCommend")
    @ResponseBody
    public List<Animal> queryAnimalsByCommend(HttpSession session){
        List<Animal> animals = service.queryAnimalsByCommend();
        return animals;
    }

    @RequestMapping("queryByDiscount")
    @ResponseBody
    public List<Animal> queryByDiscount(Integer pageNo,Integer pageRows) {
        PageHelper.startPage(pageNo,pageRows);
        List<Animal> animals = service.queryByDiscount();
        return animals;
    }
    @RequestMapping("queryByCatDog")
    @ResponseBody
    public List<Animal> queryByCatDog() {
        List<Animal> animals = service.queryByCatDog();
        return animals;
    }
    @RequestMapping("totalCount")
    @ResponseBody
    public Integer totalCount() {
        Integer count = service.totalCount();
        return count;
    }
    @RequestMapping("queryByWaterLand")
    @ResponseBody
    public List<Animal> queryByWaterLand(){
        List<Animal> animals = service.queryByWaterLand();
        return animals;
    }
    @RequestMapping("queryByBirdFish")
    @ResponseBody
    public List<Animal> queryByBirdFish(){
        List<Animal> animals = service.queryByBirdFish();
        return animals;
    }
    @RequestMapping("queryOneById")
    public String queryOneById(Integer id,HttpSession session) {
        Animal animal = service.queryOneById(id);
        session.setAttribute("details",animal);
        return "forward:/html/details.jsp";
    }
    @RequestMapping("queryByCount")
    @ResponseBody
    public List<Animal> queryByCount() {
        List<Animal> animals = service.queryByCount();
        return animals;
    }

    @RequestMapping("insertAnimal")
    @ResponseBody
    public void insertAnimal(Animal animal, MultipartFile file){
        animal.setCount(0);
        service.insertAnimal(animal,file);
    }

    @RequestMapping("updateAnimal")
    @ResponseBody
    public void updateAnimal(Animal animal){
        service.updateAnimal(animal);
    }
    @RequestMapping("queryAllLucene")
    @ResponseBody
    public List<Animal> queryAllLucene(String params,HttpSession session) {
        List<Animal> animals = service.queryAllLucene(params,session);
        return animals;
    }

    @ResponseBody
    @RequestMapping("animals")
    public List<Animal> queryAllAnimals(Integer id,HttpSession session,Integer page,Integer rows){
        PageHelper.startPage(page,rows);
        List<Animal> animals = service.queryAllAnimls(id, session);
        return animals;
    }

    @ResponseBody
    @RequestMapping("totalPage")
    public Integer totalPage(Integer id){
        Integer page = service.totalPage(id);
        if(page!=null)
            return page;
        else
            return 0;
    }

    @ResponseBody
    @RequestMapping("findAnimalCount")
    public Integer findAnimalCount(Integer id){
        Integer animalCount = service.findAnimalCount(id);
        return animalCount;
    }

    @RequestMapping("queryMoreAnimals")
    public String queryMoreAnimals(HttpSession session) {
        List<Animal> animals = service.queryMoreAnimals(session);
        return "forward:/html/commodity.jsp";
    }

    @ResponseBody
    @RequestMapping("orderBySaleCount")
    public List<Animal> orderBySaleCount(Integer id,Integer status,Integer page, Integer rows){
        PageHelper.startPage(page,rows);
        List<Animal> animals = service.orderBySaleCount(id,status);
        return animals;
    }

    @ResponseBody
    @RequestMapping("orderByPrice")
    public List<Animal> orderByPrice(Integer id,Integer status,Integer page, Integer rows){
        PageHelper.startPage(page,rows);
        List<Animal> animals = service.orderByPrice(id, status);
        return animals;
    }

    @ResponseBody
    @RequestMapping("orderByDate")
    public List<Animal> orderByDate(Integer id,Integer status,Integer page, Integer rows){
        PageHelper.startPage(page,rows);
        List<Animal> animals = service.orderByDate(id, status);
        return animals;
    }


}
