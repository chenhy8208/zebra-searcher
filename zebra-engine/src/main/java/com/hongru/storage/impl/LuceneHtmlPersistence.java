package com.hongru.storage.impl;

import com.hongru.common.lucene.IndexDao;
import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;
import com.hongru.storage.HtmlPersistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class LuceneHtmlPersistence implements HtmlPersistence{

    @Override
    public boolean filterAndSave(WebHtml webHtml) {
        if(webHtml == null) {
            logger.warn(this.getClass().getName() + " fault, webhtml = " + webHtml);
            return false;
        }

        if (!this.grabFilter.filter(webHtml)) return false; //没有通过过滤不保存

        //保存
        IndexDao indexDao = new IndexDao();
        indexDao.save(webHtml);

        return true;
    }

    private static final Logger logger = LogManager.getLogger(LuceneHtmlPersistence.class);

    private LuceneHtmlPersistence(){}

    public LuceneHtmlPersistence(GrabFilter grabFilter){
        this.grabFilter = grabFilter;
    }

    private GrabFilter grabFilter;
}