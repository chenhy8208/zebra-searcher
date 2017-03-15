package com.hongru.common.lucene.config;

import com.hongru.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by chenhongyu on 16/9/28.
 */
@Component
public class ConfigManager {

    @Autowired
    private AppConfig appConfig;

    public Directory getDirectory() {
        Path dirctoryPath = Paths.get(appConfig.getStoragePath());
        Directory directory = null;
        try {
            directory = FSDirectory.open(dirctoryPath);
        } catch (IOException e) {
            logger.error("getDirectory fault, e = " + e.getMessage());
            new RuntimeException(e);
            return null;
        }
        return directory;
    }

    public IndexSearcher getIndexSearcher() {

        if (indexSearcher != null) return indexSearcher;

        synchronized (ConfigManager.class) {
            try {
                if (indexSearcher == null) {
                    synchronized (ConfigManager.class) {
                        DirectoryReader directoryReader = DirectoryReader.open(getDirectory());
                        indexSearcher = new IndexSearcher(directoryReader);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return indexSearcher;
    }

    /**
     * 清除掉indexSearcher
     */
    public void cleanup() {
        if (indexSearcher != null)
        {
            indexSearcher = null;
            System.out.println(new Date() + ":indexSearcher cleaned...");
        }
    }

    public IKAnalyzer getAnalyzer() {
        return new IKAnalyzer(true); //是否开启智能分词
    }

    private static IndexSearcher indexSearcher;
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);

}
