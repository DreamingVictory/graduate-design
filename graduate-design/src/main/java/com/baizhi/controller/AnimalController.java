package com.baizhi.controller;

import com.baizhi.entity.Animal;
import com.baizhi.service.AnimalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    AnimalService service;

    @RequestMapping("queryAllAnimal")
    public String queryAllAnimal(HttpSession session){
        List<Animal> animals = service.queryAllAnimal();
        session.setAttribute("list",animals);
        return "forward:/html/commodity.jsp";
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
}
