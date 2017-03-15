package com.hongru.listener;

import com.hongru.common.lucene.config.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenhongyu on 2017/3/5.
 */
@Component
public class UpdateIndexSearcherBehavior implements Runnable {

    @Override
    public void run() {
        while (true) {
            //定时更新一下indexSearcher，要不然搜索的索引不会变
            configManager.cleanup();

            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private ConfigManager configManager;
    private final static long INTERVAL =  60 * 1000;
}
