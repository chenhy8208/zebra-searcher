package com.hongru.spider.impl;

import com.hongru.common.lucene.conf.ConfigManager;
import com.hongru.config.AppConfig;
import com.hongru.spider.SpiderLauncher;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import static com.hongru.config.AppConfig.maxDepthOfCrawling;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class CrawlerLauncher implements SpiderLauncher {

    @Override
    public void spiderLaunch() {

        CrawlConfig config = new CrawlConfig();
        config.setResumableCrawling(true);  //可恢复
        config.setUserAgentString(userAgent);

        config.setCrawlStorageFolder(AppConfig.crawlStorageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setPolitenessDelay(AppConfig.politenessDelay);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            //构造出错,程序停止
            throw new RuntimeException(e);
        }

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        for (String url :
                AppConfig.seeds) {
            controller.addSeed(url);
        }

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(this.crawlerClass, AppConfig.numberOfCrawlers);
    }

    @Override
    public void shutdown() {
        controller.shutdown();
        controller.waitUntilFinish();
        ConfigManager.closeIndexWriter();
    }

    public CrawlerLauncher(Class<? extends WebCrawler> crawlerClass) {
        this.crawlerClass = crawlerClass;
    }

    private CrawlerLauncher(){}

    //模拟User-Agent
    private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";

    private CrawlController controller;

    private Class<? extends WebCrawler> crawlerClass;
}
