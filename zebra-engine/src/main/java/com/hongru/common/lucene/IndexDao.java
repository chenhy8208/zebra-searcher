package com.hongru.common.lucene;

import com.hongru.common.lucene.conf.ConfigManager;
import com.hongru.domain.WebHtml;
import com.hongru.storage.impl.LuceneHtmlPersistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class IndexDao {

    public void save(WebHtml webHtml)
    {
        Document doc = HTMLDocumentUtils.htmlToDocument(webHtml);
        IndexWriter indexWriter = ConfigManager.getIndexWriter();
        try {
            indexWriter.addDocument(doc); // 操作
            indexWriter.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            ConfigManager.closeIndexWriter();
        }
    }

    public void update(WebHtml webHtml)
    {
        IndexWriter indexWriter = ConfigManager.getIndexWriter();
        try {
            Term term = new Term("url", webHtml.getUrl());
            Document doc = HTMLDocumentUtils.htmlToDocument(webHtml);
            indexWriter.updateDocument(term, doc); // 更新就是先删除再添加
            indexWriter.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            ConfigManager.closeIndexWriter();
        }
    }

    public void delete(String url){
        IndexWriter indexWriter = ConfigManager.getIndexWriter();
        try {
            Term term = new Term("url", url);
            indexWriter.deleteDocuments(term); // 删除含有指定Term的Document数据
            indexWriter.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            ConfigManager.closeIndexWriter();
        }
    }

    private static final Logger logger = LogManager.getLogger(IndexDao.class);

}