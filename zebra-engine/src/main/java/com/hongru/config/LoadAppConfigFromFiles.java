package com.hongru.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 将配置文件中的信息加载到全局的配置类中
 * Created by chenhongyu on 2016/10/12.
 */
public class LoadAppConfigFromFiles {

    private static Path configFilePath(String filePath) {
        Path configFile = Paths.get(filePath);
        if (!Files.exists(configFile) || Files.isDirectory(configFile)) {
            configFile = Paths.get(filePath, "/info.properties");
            if (!Files.exists(configFile) || Files.isDirectory(configFile)) {
                return null;
            }
        }

        return configFile;
    }

    public static boolean load(String filePath) {
        if (StringUtils.isBlank(filePath)) return false;

        Path configFile = configFilePath(filePath);
        if (configFile == null) return false;

        try {
            Configuration configuration = new PropertiesConfiguration(configFile.toFile());
            return initAppConfig(configuration);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }

    private static boolean initAppConfig(Configuration configuration) throws Exception {
        if(configuration == null) return false;
        AppConfig.crawlStorageFolder = configuration.getString("crawlStorageFolder");
        AppConfig.maxDepthOfCrawling = configuration.getInt("maxDepthOfCrawling");
        AppConfig.mergePolicy_maxMergeDocs = configuration.getInt("mergePolicy_maxMergeDocs");
        AppConfig.mergePolicy_mergeFactor = configuration.getInt("mergePolicy_mergeFactor");
        AppConfig.numberOfCrawlers = configuration.getInt("numberOfCrawlers");
        AppConfig.politenessDelay = configuration.getInt("politenessDelay");
        AppConfig.storagePath = configuration.getString("storagePath");

        AppConfig.redisServers = configuration.getString("redis.server");
        AppConfig.redisPassword = configuration.getString("redis.password");

        AppConfig.seeds = configuration.getStringArray("seeds");
        if (AppConfig.seeds == null || AppConfig.seeds.length <= 0) {
            AppConfig.seeds = new String[]{configuration.getString("seeds")};
            if (AppConfig.seeds == null || AppConfig.seeds.length <= 0){
                return false;
            }
        }

        return true;
    }

    private static final Logger logger = LogManager.getLogger(LoadAppConfigFromFiles.class);
}
