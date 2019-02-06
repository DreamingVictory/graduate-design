package com.baizhi.controller;

import com.baizhi.entity.Animal;
import com.baizhi.service.AnimalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    AnimalService service;
    @RequestMapping("queryAllAnimal")
    @ResponseBody
    public List<Animal> queryAllAnimal(HttpSession session){
        List<Animal> animals = service.queryAllAnimal();
        session.setAttribute("list",animals);
        return animals;
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
        System.out.println(animals);
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
}
