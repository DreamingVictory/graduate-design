package com.baizhi.conf;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneUtil {
    private static Directory directory = null;//索引库
    private static IndexWriterConfig indexWriterConfig = null;//索引写入器的相关配置
    private static Analyzer analyzer = null;
    private static final Version version = Version.LUCENE_44;

    static {
        try {
            directory = FSDirectory.open(new File("E:/Code/index"));
            analyzer = new IKAnalyzer();
            indexWriterConfig = new IndexWriterConfig(version, analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取indexWriter
    public static IndexWriter getIndexWriter() {
        try {
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);//创建索引写入器
            return indexWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取indexSearch
    public static IndexSearcher getIndexSearch() {
        try {
            IndexReader indexReader = DirectoryReader.open(directory);//文件读出流，将索引库中的内容通过文件读出流读出
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            return indexSearcher;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void commit(IndexWriter indexWriter) {
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(IndexWriter indexWriter) {
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
