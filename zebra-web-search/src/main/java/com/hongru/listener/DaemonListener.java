package com.hongru.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by chenhongyu on 2017/3/5.
 */
@Service("DaemonListener")
public class DaemonListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if(event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
        {
            new Thread(updateIndexSearcherBehavior).start();
        }
    }

    @Autowired
    private UpdateIndexSearcherBehavior updateIndexSearcherBehavior;
}
