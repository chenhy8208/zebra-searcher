package com.hongru.loading.impl;

import com.hongru.domain.WebHtml;
import com.hongru.loading.Loading;
import com.hongru.parse.HTMLDateParse;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class CrawlerLoading implements Loading{


    @Override
    public WebHtml loading(Page page) {
        String url = page.getWebURL().getURL();
        int statusCode = page.getStatusCode();
        org.apache.http.Header[] headers = page.getFetchResponseHeaders();
        Object htmlParseData = page.getParseData();

        if (StringUtils.isBlank(url)) {
            logger.warn("Loading html fault,Because url = " + url);
            return null;
        }

        if (htmlParseData == null) {
            logger.warn("Loading html fault,Because htmlParseData = " + htmlParseData);
            return null;
        }

        HtmlParseData parseData = (HtmlParseData)htmlParseData;

        WebHtml webHtml = null;
        try {
            webHtml = new WebHtml();
            webHtml.setDocId(page.getWebURL().getDocid()); //唯一标示
            webHtml.setUrl(url);
            webHtml.setCrawlTime(new Date());  //抓取时间
            webHtml.setHtml(parseData.getHtml());
            webHtml.setHtmlLength(parseData.getHtml().length());
            webHtml.setMetaDescription(parseData.getMetaTags().get("description"));
            webHtml.setMetaKeyword(parseData.getMetaTags().get("keywords"));
            webHtml.setMetaTitle(parseData.getMetaTags().get("dc:title"));
            webHtml.setNumberOfOutgoingLinks(parseData.getOutgoingUrls().size());
            webHtml.setStatusCode(statusCode);
            webHtml.setContentType(parseData.getMetaTags().get("content-type"));
            webHtml.setEncoding(parseData.getMetaTags().get("content-encoding"));
            webHtml.setAuthor(parseData.getMetaTags().get("author"));
            webHtml.setPageUpdateTime(HTMLDateParse.parseHTMLPublishDate(parseData.getHtml()));
            loadHeaders(headers, webHtml);
        } catch (Exception e) {
            logger.error("web url = " + url + " | " + e.getMessage());
            return null;
        }

        return webHtml;
    }

    /**
     * 处理一些headers中才有的数据,比如页面最后更新时间
     * @param headers
     * @param webHtml
     */
    private void loadHeaders(org.apache.http.Header[] headers, WebHtml webHtml) {
        webHtml.setPageLastModified(new Date(0));  //页面最后更新时间默认值 1970年

        if(headers == null || headers.length <= 0) {
            logger.error("CrawlerLoading loadHeaders headers is null");
            return;
        };

        for (Header header:
                headers) {
            String name = header.getName();
            if (isLastModified(name)) {
                String value = header.getValue();
                Date lastModifiedDate = DateUtils.parseDate(value);
                //设置为从header中获取的页面最后更新时间
                webHtml.setPageLastModified(lastModifiedDate);
                break;
            }
        }
    }

    private boolean isLastModified(String name) {
        return StringUtils.equalsIgnoreCase(name, "Last-Modified");
    }

    public CrawlerLoading() {

    }

    private static final Logger logger = LogManager.getLogger(CrawlerLoading.class);
}
