package com.hongru.config;


import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

/**
 * Created by chenhongyu on 16/9/30.
 */
public class AppConfig {

    //lucene数据保存路径
    private String storagePath;

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    public int getSearchResultPagesize() {
        return searchResultPagesize;
    }

    public void setSearchResultPagesize(int searchResultPagesize) {
        this.searchResultPagesize = searchResultPagesize;
    }

    //每页显示多少条
    private int searchResultPagesize;

    //lucene版本
    public static final Version currentLuceneVersion = Version.LUCENE_6_2_1;

}
