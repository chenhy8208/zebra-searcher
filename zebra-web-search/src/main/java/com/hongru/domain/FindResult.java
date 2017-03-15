package com.hongru.domain;

/**
 * Created by chenhongyu on 2016/10/8.
 */
public class FindResult<T> {
    private T webHtml;
    private float relativity;
    private String relativityStr;

    public T getWebHtml() {
        return webHtml;
    }

    public void setWebHtml(T webHtml) {
        this.webHtml = webHtml;
    }

    public float getRelativity() {
        return relativity;
    }

    public void setRelativity(float relativity) {
        this.relativity = relativity;
    }

    public String getRelativityStr() {
        return relativityStr;
    }

    public void setRelativityStr(String relativityStr) {
        this.relativityStr = relativityStr;
    }
}
