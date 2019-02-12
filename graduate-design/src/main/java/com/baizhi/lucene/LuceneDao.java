package com.baizhi.lucene;

import com.baizhi.entity.Animal;

import java.util.List;

public interface LuceneDao {
    public void createIndexToAnimal(Animal animal);

    public List<Animal> queryAllLucene(String params);

    public void deleteIndex(Integer id);
}
