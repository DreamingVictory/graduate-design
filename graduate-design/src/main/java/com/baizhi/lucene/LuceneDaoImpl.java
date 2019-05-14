package com.baizhi.lucene;

import com.baizhi.conf.LuceneUtil;
import com.baizhi.entity.Animal;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LuceneDaoImpl implements LuceneDao {

    public Document getDocFromAnimal(Animal animal){
        Document document = new Document();

        document.add(new IntField("id", animal.getId(), Field.Store.YES));
        document.add(new TextField("title", animal.getTitle(), Field.Store.YES));
        document.add(new StringField("img", animal.getImg(), Field.Store.YES));
        document.add(new DoubleField("ciurPic",animal.getPrice(), Field.Store.YES));
        document.add(new DoubleField("oriPic",animal.getPrice(), Field.Store.YES));
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
    public Animal getAnimalFromDoc(Document document, Query query) {

        try {
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Scorer scorer = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);

            Animal animal = new Animal();
            int id = Integer.valueOf(document.get("id"));
            String bestFragment = highlighter.getBestFragment(new IKAnalyzer(), "id", document.get("id"));
            if (bestFragment == null) {
                animal.setId(id);
            } else {
                animal.setId(Integer.valueOf(bestFragment));
            }
            String bestFragment1 = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
            if (bestFragment1 == null) {
                animal.setTitle(document.get("title"));
            } else {
                animal.setTitle(bestFragment1);
            }
            String bestFragment2 = highlighter.getBestFragment(new IKAnalyzer(), "img", document.get("img"));
            if (bestFragment2 == null) {
                animal.setImg(document.get("img"));
            } else {
                animal.setImg(bestFragment2);
            }
            String bestFragment3 = highlighter.getBestFragment(new IKAnalyzer(), "discount", document.get("discount"));
            if(bestFragment3==null){
                animal.setDiscount(document.get("discount"));
            }else{
                animal.setDiscount(bestFragment3);
            }
            int count = Integer.valueOf(document.get("count"));
            String bestFragment4 = highlighter.getBestFragment(new IKAnalyzer(), "count", document.get("count"));
            if (bestFragment4 == null) {
                animal.setCount(count);
            } else {
                animal.setCount(Integer.valueOf(bestFragment4));
            }

            String bestFragment5 = highlighter.getBestFragment(new IKAnalyzer(), "description", document.get("description"));
            if (bestFragment5 == null) {
                animal.setDescription(document.get("description"));
            } else {
                animal.setDescription(bestFragment5);
            }

            String bestFragment6 = highlighter.getBestFragment(new IKAnalyzer(), "status", document.get("status"));
            if (bestFragment6 == null) {
                animal.setStatus(document.get("status"));
            } else {
                animal.setStatus(bestFragment6);
            }

            String bestFragment7 = highlighter.getBestFragment(new IKAnalyzer(), "recommand", document.get("recommand"));
            if (bestFragment7 == null) {
                animal.setRecommand(document.get("recommand"));
            } else {
                animal.setRecommand(bestFragment7);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date productDate = simpleDateFormat.parse(document.get("pubDate"));
            animal.setPubDate(productDate);
            animal.setPrice(Double.valueOf(document.get("ciurPic")));
            animal.setPrice(Double.valueOf(document.get("oriPic")));
            animal.setCategoryId(Integer.valueOf(document.get("categoryId")));


            return animal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}




    @Override
    public void createIndexToAnimal(Animal animal) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromArticle = getDocFromAnimal(animal);
        try {
            indexWriter.addDocument(docFromArticle);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }

    }

    @Override
    public List<Animal> queryAllLucene(String params) {
        try {
            String[] strs={"title","discount","count","description","status","recommand"};
            MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strs, new IKAnalyzer());
            IndexSearcher indexSearch = LuceneUtil.getIndexSearch();
            Query query = multiFieldQueryParser.parse(params);
            TopDocs topDocs = indexSearch.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            List<Animal> list=new ArrayList<>();
            for(int i=0;i<scoreDocs.length;i++){
                ScoreDoc scoreDoc=scoreDocs[i];
                int doc=scoreDoc.doc;
                Document document = indexSearch.doc(doc);
                Animal animal = getAnimalFromDoc(document, query);
                list.add(animal);
            }
            return list;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteIndex(Integer id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id.toString()));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }
}
