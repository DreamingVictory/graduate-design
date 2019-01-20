package com.baizhi.controller;

import com.baizhi.entity.Animal;
import com.baizhi.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public List<Animal> queryAnimalsByCommend(HttpSession session){
        List<Animal> animals = service.queryAnimalsByCommend();
        session.setAttribute("animals",animals);
        System.out.println("2222222222222222222222222222222"+animals.size());
        return animals;
    }
}
