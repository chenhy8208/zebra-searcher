package com.hongru.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhongyu on 2017/3/5.
 */
public class SpiderRecord implements Serializable{
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getPerhapsReviewDate() {
        return perhapsReviewDate;
    }

    public void setPerhapsReviewDate(Date perhapsReviewDate) {
        this.perhapsReviewDate = perhapsReviewDate;
    }

    private String url;
    private Date createDate;
    private int level;
    private Date perhapsReviewDate;
}
