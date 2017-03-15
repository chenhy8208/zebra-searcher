package com.hongru.config;

import org.apache.lucene.util.Version;

/**
 * Created by chenhongyu on 16/9/30.
 */
public class AppConfig {

    //lucene数据保存路径
    public static String storagePath;

    //爬虫数据保存目录
    public static String crawlStorageFolder;

    //爬虫数量
    public static int numberOfCrawlers;

    /**
     * A -> B -> C -> D
     Since, "A" is a seed page, it will have a depth of 0.
     "B" will have depth of 1 and so on.
     You can set a limit on the depth of pages that crawler4j crawls.
     For example, if you set this limit to 2,
     it won't crawl page "D". To set the maximum depth you can use:
     */
    //抓取深度
    public static int maxDepthOfCrawling;

    //延迟毫秒数 Politeness delay in milliseconds (delay between sending two requests to the same host).默认200毫秒.
    public static int politenessDelay;

    //网站初始种子
    public static String[] seeds;

    //设置segment添加文档(Document)时的合并频率
    // 值较小,建立索引的速度就较慢
    // 值较大,建立索引的速度就较快,>10适合批量建立索引
    public static int mergePolicy_mergeFactor;

    //设置segment最大合并文档(Document)数
    //值较小有利于追加索引的速度
    //值较大,适合批量建立索引和更快的搜索
    public static int mergePolicy_maxMergeDocs;

    //lucene版本
    public static final Version currentLuceneVersion = Version.LUCENE_6_2_1;

    //redis server，多个用,分隔
    public static String redisServers;
    public static String redisPassword;
}
