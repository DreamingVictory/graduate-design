package com.baizhi;

import com.baizhi.conf.LuceneUtil;
import com.baizhi.entity.Animal;
import com.baizhi.entity.Category;
import com.baizhi.lucene.LuceneDaoImpl;
import com.baizhi.mapper.AnimalMapper;
import com.baizhi.service.AnimalService;
import com.baizhi.service.CategoryService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    @Autowired
    LuceneDaoImpl luceneDaoImpl;
    @Autowired
    AnimalMapper animalMapper;
    @Autowired
    FastFileStorageClient fastFileStorageClient;

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
    @Test
    public void test3(){
        List<Animal> animals = animalMapper.selectAll();
        IndexWriter indexWriter =   LuceneUtil.getIndexWriter();
        for (Animal animal : animals) {
            System.out.println(animal);
            Document document = getDocFromAnimal(animal);
            try {
                indexWriter.addDocument(document);
                System.out.println("----------------------------"+document);
            } catch (IOException e) {
                e.printStackTrace();
                LuceneUtil.rollback(indexWriter);
            }
        }
        LuceneUtil.commit(indexWriter);

    }

    public Document getDocFromAnimal(Animal animal){
        Document document = new Document();

        document.add(new IntField("id", animal.getId(), Field.Store.YES));
        document.add(new TextField("title", animal.getTitle(), Field.Store.YES));
        document.add(new StringField("img", animal.getImg(), Field.Store.YES));
        document.add(new DoubleField("ciurPic",animal.getCiurPic(), Field.Store.YES));
        document.add(new DoubleField("oriPic",animal.getOriPic(), Field.Store.YES));
        document.add(new TextField("discount", animal.getDiscount(), Field.Store.YES));
        document.add(new IntField("count", animal.getCount(), Field.Store.YES));
        document.add(new IntField("categoryId", animal.getCategoryId(), Field.Store.YES));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(animal.getPubDate());
        document.add(new StringField("pubDate", format, Field.Store.YES));
        document.add(new TextField("description", animal.getDescription(), Field.Store.YES));
        document.add(new TextField("status",animal.getStatus(), Field.Store.YES));
        document.add(new TextField("recommand",animal.getRecommand(), Field.Store.YES));
        return document;
    }
    @Test
    public void test4(){
        List<Animal> animals = animalMapper.selectAll();
        for (Animal animal : animals) {
            luceneDaoImpl.deleteIndex(animal.getId());
        }


    }
    @Test
    public void test5(){
        List<Animal> animals = luceneDaoImpl.queryAllLucene("狗");
        System.out.println(animals.size());
        for (Animal animal : animals) {
            System.out.println(animal);
        }

    }
    @Test
    public void test6() throws FileNotFoundException {
        List<Animal> animals = animalMapper.selectAll();
        for (Animal animal : animals) {
            File file = new File("E:/Code/resource/graduate-design/graduate-design/src/main/webapp"+animal.getImg());
            StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);
            animal.setImg(storePath.getFullPath());
            animalMapper.updateByPrimaryKeySelective(animal);
        }
    }


}

