package com.baizhi;

import com.baizhi.entity.Animal;
import com.baizhi.entity.Category;
import com.baizhi.service.AnimalService;
import com.baizhi.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraduateDesignApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    CategoryService service;
    @Autowired
    AnimalService animalService;

    @Test
    public void test(){
        redisTemplate.opsForValue().set("test", "112233");
        String aThis = redisTemplate.opsForValue().get("test");
        System.out.println(aThis);
    }
    @Test
    public void test1(){
        List<Category> categories = service.queryAllCategory();
        for (Category category : categories) {
            System.out.println(category);

        }
    }
    @Test
    public void test2(){
        List<Animal> animals = animalService.queryAnimalsByCommend();
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }


}

