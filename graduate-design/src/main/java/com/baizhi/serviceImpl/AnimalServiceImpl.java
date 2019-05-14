package com.baizhi.serviceImpl;

import com.baizhi.dto.PageBeanDto;
import com.baizhi.entity.Animal;
import com.baizhi.entity.Category;
import com.baizhi.lucene.LuceneDao;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.mapper.CategoryMapper;
import com.baizhi.service.AnimalService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    CategoryMapper categoryMapper;

   @Override
    public PageBeanDto<Animal> queryAllAnimal() {
        List<Animal> animals = animalMapper.selectAll();
        int count = animalMapper.selectCount(null);
        PageBeanDto<Animal> pageBeanDto=new PageBeanDto<>();
        pageBeanDto.setRows(animals);
        pageBeanDto.setTotal(count);
        return pageBeanDto;
    }

    @Override
    public List<Animal> queryAllAnimls(Integer id,HttpSession session){
        if(id!=null){
            Category category = categoryMapper.selectByPrimaryKey(id);
            if(category.getParentId()!=null){
                Animal animal=new Animal();
                animal.setCategoryId(id);
                List<Animal> animalList = animalMapper.select(animal);
                if(!animalList.isEmpty()) return animalList;
                else return Collections.EMPTY_LIST;
            }else{
                List<Animal> animals = animalMapper.queryFirstAnimal(id);
                if(!animals.isEmpty()) return animals;
                else return Collections.EMPTY_LIST;
            }
        }
        List<Animal> animals = animalMapper.selectAll();
        if(animals.isEmpty()) return Collections.EMPTY_LIST;
        return animals;
    }

    public Integer totalPage(Integer id){
       if(id!=null){
           Category category = categoryMapper.selectByPrimaryKey(id);
           if(category.getParentId()!=null){
               Animal animal=new Animal();
               animal.setCategoryId(id);
               int count = animalMapper.selectCount(animal);
               if(count%9==0) return count/9;
               else  return count/9+1;
           }else{
               List<Animal> animals = animalMapper.queryFirstAnimal(id);
               int count=animals.size();
               if(count%9==0) return count/9;
               else  return count/9+1;
           }
       }
        int count = animalMapper.selectCount(animalMapper.selectByPrimaryKey(id));
        if(count%9==0) return count/9;
        else  return count/9+1;
    }

    @Override
    public Integer findAnimalCount(Integer id){
        if(id!=null){
            Category category = categoryMapper.selectByPrimaryKey(id);
            if(category.getParentId()!=null){
                Animal animal=new Animal();
                animal.setCategoryId(id);
                int count = animalMapper.selectCount(animal);
                return count;
            }else{
                List<Animal> animals = animalMapper.queryFirstAnimal(id);
                int count=animals.size();
                return count;
            }
        }
        int count = animalMapper.selectCount(animalMapper.selectByPrimaryKey(id));
        return count;
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
    public List<Animal> queryMoreAnimals(HttpSession session) {
        List<Animal> animals = animalMapper.selectAll();
        int count = animals.size();
        session.setAttribute("animals",animals);
        session.setAttribute("count",count);
        return animals;
    }

    @Override
    public List<Animal> orderBySaleCount(Integer id,Integer status) {
        if(id!=null){
            Category category = categoryMapper.selectByPrimaryKey(id);
            if(category.getParentId()!=null){

                Example example=new Example(Animal.class);
                if(status.equals(0))
                    example.setOrderByClause("count DESC");
                else
                    example.setOrderByClause("count ASC");

                example.createCriteria().andEqualTo("categoryId",id);
                List<Animal> animalList = animalMapper.selectByExample(example);

                if(!animalList.isEmpty()) return animalList;
                else return Collections.EMPTY_LIST;
            }else{
                List<Integer> list = categoryMapper.categoryChildren(id);
                Example example=new Example(Animal.class);
                if(status.equals(0))
                    example.setOrderByClause("count DESC");
                else
                    example.setOrderByClause("count ASC");
                example.createCriteria().andIn("categoryId",list);
                List<Animal> animalList = animalMapper.selectByExample(example);

                if(!animalList.isEmpty()) return animalList;
                else return Collections.EMPTY_LIST;
            }
        }
        Example example=new Example(Animal.class);
        if(status.equals(0))
            example.setOrderByClause("count DESC");
        else
            example.setOrderByClause("count ASC");
        example.createCriteria().andEqualTo("categoryId",id);
        List<Animal> animalList = animalMapper.selectByExample(example);

        if(!animalList.isEmpty()) return animalList;
        else return Collections.EMPTY_LIST;
    }

    @Override
    public List<Animal> orderByPrice(Integer id,Integer status) {
        if(id!=null){
            Category category = categoryMapper.selectByPrimaryKey(id);
            if(category.getParentId()!=null){

                Example example=new Example(Animal.class);
                if(status.equals(0))
                    example.setOrderByClause("ciur_pic DESC");
                else
                    example.setOrderByClause("ciur_pic ASC");

                example.createCriteria().andEqualTo("categoryId",id);
                List<Animal> animalList = animalMapper.selectByExample(example);

                if(!animalList.isEmpty()) return animalList;
                else return Collections.EMPTY_LIST;
            }else{
                List<Integer> list = categoryMapper.categoryChildren(id);
                Example example=new Example(Animal.class);
                if(status.equals(0))
                    example.setOrderByClause("ciur_pic DESC");
                else
                    example.setOrderByClause("ciur_pic ASC");
                example.createCriteria().andIn("categoryId",list);
                List<Animal> animalList = animalMapper.selectByExample(example);

                if(!animalList.isEmpty()) return animalList;
                else return Collections.EMPTY_LIST;
            }
        }
        Example example=new Example(Animal.class);
        if(status.equals(0))
            example.setOrderByClause("ciur_pic DESC");
        else
            example.setOrderByClause("ciur_pic ASC");
        example.createCriteria().andEqualTo("categoryId",id);
        List<Animal> animalList = animalMapper.selectByExample(example);

        if(!animalList.isEmpty()) return animalList;
        else return Collections.EMPTY_LIST;
    }

   @Override
    public List<Animal> orderByDate(Integer id,Integer status) {
       if(id!=null){
           Category category = categoryMapper.selectByPrimaryKey(id);
           if(category.getParentId()!=null){

               Example example=new Example(Animal.class);
               if(status.equals(0))
                   example.setOrderByClause("pub_date DESC");
               else
                   example.setOrderByClause("pub_date ASC");

               example.createCriteria().andEqualTo("categoryId",id);
               List<Animal> animalList = animalMapper.selectByExample(example);

               if(!animalList.isEmpty()) return animalList;
               else return Collections.EMPTY_LIST;
           }else{
               List<Integer> list = categoryMapper.categoryChildren(id);
               Example example=new Example(Animal.class);
               if(status.equals(0))
                   example.setOrderByClause("pub_date DESC");
               else
                   example.setOrderByClause("pub_date ASC");
               example.createCriteria().andIn("categoryId",list);
               List<Animal> animalList = animalMapper.selectByExample(example);

               if(!animalList.isEmpty()) return animalList;
               else return Collections.EMPTY_LIST;
           }
       }
       Example example=new Example(Animal.class);
       if(status.equals(0))
           example.setOrderByClause("pub_date DESC");
       else
           example.setOrderByClause("pub_date ASC");
       example.createCriteria().andEqualTo("categoryId",id);
       List<Animal> animalList = animalMapper.selectByExample(example);

       if(!animalList.isEmpty()) return animalList;
       else return Collections.EMPTY_LIST;
    }

    @Override
    public List<Animal> queryBySale(){
        Example example=new Example(Animal.class);
        example.setOrderByClause("count DESC");

        List<Animal> animalList = animalMapper.selectByExample(example);
        List<Animal> list=new ArrayList<>();
        for (int i = 0; i < animalList.size(); i++) {
            list.add(animalList.get(i));
            if(list.size()==3) break;
        }

        return list;
    }


}
