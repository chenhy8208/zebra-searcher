package com.hongru.filter.impl;

import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by chenhongyu on 16/9/28.
 *
 */
public class XHRFilter implements GrabFilter {

    @Override
    public boolean filter(WebHtml webHtml) {
        return true;
        /*
        for (String keyword: keywords) {
            //标题中含有关键词
            if (StringUtils.contains(webHtml.getMetaTitle(), keyword) &&
                    StringUtils.contains(webHtml.getMetaTitle(), "招标")) {
                return true;
            }
        }

        return false;
        */
    }

    private String[] keywords = {
            "网站建设",
            "网站建设招标",
            "网站平台",
            "门户网",
            "网站项目",
            "网站群"
    };
}