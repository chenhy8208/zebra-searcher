package com.hongru;

import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.XHRCrawler;
import com.hongru.spider.impl.CrawlerLauncher;

/**
 * 启动类
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //初始化加载
        AppInit.init();

        final SpiderLauncher spiderLauncher  = new CrawlerLauncher(XHRCrawler.class);
        spiderLauncher.spiderLaunch();

        //关闭爬虫
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                spiderLauncher.shutdown();
            }
        });
    }
}
