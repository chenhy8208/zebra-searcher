package com.hongru.parse;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhongyu on 2016/10/17.
 */
public class RegExDate {
    private List<String> regEx;
    private Map<String, Integer> dateStrs; //key是日期字符串,value是所在字符串的位置
    private String datePrefix;  //日期前缀,比如发布日期,日期这种

    public List<String> getRegEx() {
        return regEx;
    }

    public void setRegEx(List<String> regEx) {
        this.regEx = regEx;
    }

    public Map<String, Integer> getDateStrs() {
        return dateStrs;
    }

    public void setDateStrs(Map<String, Integer> dateStrs) {
        this.dateStrs = dateStrs;
    }

    public String getDatePrefix() {
        return datePrefix;
    }

    public void setDatePrefix(String datePrefix) {
        this.datePrefix = datePrefix;
    }
}
