package com.hongru.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class WebHtml implements Serializable {
    private String url;
    private Date crawlTime;
    private Date pageUpdateTime;  //页面更新的时间
    private Date pageLastModified;  //页面最后修改时间
    private String metaTitle;
    private String metaKeyword;
    private String metaDescription;
    private String html;
    private String text;  //有用的片段
    private String encoding;  //编码
    private String contentType;  //内容类型
    private String author;  //作者
    private int statusCode;  //网页返回的状态码
    private int docId; //网页的唯一标示

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public Date getPageLastModified() {
        return pageLastModified;
    }

    public void setPageLastModified(Date pageLastModified) {
        this.pageLastModified = pageLastModified;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private int htmlLength;  //网页字符长度
    private int numberOfOutgoingLinks;  //外链数量

    public Date getPageUpdateTime() {
        return pageUpdateTime;
    }

    public void setPageUpdateTime(Date pageUpdateTime) {
        this.pageUpdateTime = pageUpdateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(Date crawlTime) {
        this.crawlTime = crawlTime;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getHtmlLength() {
        return htmlLength;
    }

    public void setHtmlLength(int htmlLength) {
        this.htmlLength = htmlLength;
    }

    public int getNumberOfOutgoingLinks() {
        return numberOfOutgoingLinks;
    }

    public void setNumberOfOutgoingLinks(int numberOfOutgoingLinks) {
        this.numberOfOutgoingLinks = numberOfOutgoingLinks;
    }
}
