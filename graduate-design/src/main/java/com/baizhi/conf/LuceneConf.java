package com.baizhi.conf;


import com.baizhi.lucene.LuceneDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConf {
    @Bean
    public LuceneDaoImpl getLuceneDaoImpl() {
        return new LuceneDaoImpl();
    }
}
