package com.hongru.spider;

import com.hongru.common.model.SpiderRecord;
import com.hongru.common.mongo.MongoUtil;
import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;
import com.hongru.filter.impl.XHRFilter;
import com.hongru.loading.Loading;
import com.hongru.loading.impl.CrawlerLoading;
import com.hongru.storage.HtmlPersistence;
import com.hongru.storage.impl.LuceneHtmlPersistence;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by chenhongyu on 16/9/28.
 *
 */
public class XHRCrawler extends WebCrawler {

    private static final Logger logger = LogManager.getLogger(XHRCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|docx?|xlsx?|pptx?"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            logger.info("visit url:" + url);

            //数据装入
            Loading loading = new CrawlerLoading();
            WebHtml webHtml = loading.loading(page);

            GrabFilter grabFilter = new XHRFilter();
            //过滤持久化
            HtmlPersistence htmlPersistence = new LuceneHtmlPersistence(grabFilter);
            final boolean b = htmlPersistence.filterAndSave(webHtml);


            //插入mongodb,用来以后做更新
            SpiderRecord record = new SpiderRecord();
            record.setUrl(webHtml.getUrl());
            //这个页面有关键数据，设置级别更高
            //TODO 当前如果第一个页面没有关键数据，后面的即使有也无法再插入mongo了，因为url唯一
            record.setLevel(b? 1: 0);
            record.setCreateDate(new Date());
            record.setPerhapsReviewDate(new Date());

            MongoUtil.insertVisitRecord(record);

        }
    }
}
