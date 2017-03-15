package com.hongru.parse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenhongyu on 2016/10/14.
 */
public class HTMLDateParse {

    /**
     * 从网页中解析出当前网页可能的发布时间
     * @param htmlText
     * @return
     */
    public static Date parseHTMLPublishDate(String htmlText) {
        Date defaultDate = new Date(0);
        if (regExDates == null) init();

        if (regExDates != null) {
            for (RegExDate regExDate:regExDates) {
                Date regDate = regDate(regExDate, htmlText);
                if (regDate != null) return regDate;
            }
        }

        return defaultDate;
    }

    //匹配时间
    private static Date regDate(RegExDate regExDate, String htmlText) {
        if (StringUtils.isBlank(regExDate.getDatePrefix()))
            return emptyPrefixRegDate(regExDate, htmlText);
        else
            return NotEmptyPrefixRegDate(regExDate, htmlText);
    }


    private static Date emptyPrefixRegDate(RegExDate regExDate, String htmlText) {
        List<Date> dates = new ArrayList<>();

        for (String reg: regExDate.getRegEx()) {
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(htmlText);
            while (matcher.find()){
                try {
                    Date date = getDateFromMatcher(matcher);
                    dates.add(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.warn("Find Date fault,err = " + e.getMessage());
                }
            }

        }

        if (dates!=null) {
            //TODO 如果有很多日期,而且没有关键词,那么就返回第一个匹配
            return dates.get(0);
        }

        return null;
    }

    private static Date NotEmptyPrefixRegDate(RegExDate regExDate, String htmlText) {
        for (String reg: regExDate.getRegEx()) {
            String regStr = regExDate.getDatePrefix()+"\\s*[:：\\s]\\s*"+reg;

            Pattern pattern = Pattern.compile(regStr);
            Matcher matcher = pattern.matcher(htmlText);
            if (matcher.find()){
                try {
                    Date date = getDateFromMatcher(matcher);
                    return date;
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.warn("Find Date fault,err = " + e.getMessage());
                    return null;
                }
            }

        }

        return null;
    }

    //解析日期
    private static Date getDateFromMatcher(Matcher matcher) throws ParseException {
        String year = matcher.group(1);
        String month = matcher.group(2);
        String day = matcher.group(3);

        Date date = sdf.parse(year+"-"+month+"-"+day);
        return date;
    }

    private static void init() {
        if (regExDates == null) {
            synchronized (HTMLDateParse.class) {
                List<String> regs = allPureDateRegEx();

                if (regExDates == null) {
                    loadRegExDate(regs);
                }
            }
        }

    }

    /**
     * 正则规则设置
     * @param regs
     */
    private static void loadRegExDate(List<String> regs ) {
        regExDates = new ArrayList<>();
        String[] datePrefixs = new String[]{
                "发布日期",
                "发布时间",
                "更新日期",
                "更新时间",
                "招标日期",
                "招标时间",
                "最后更新时间",
                ""
        };

        for (String pre : datePrefixs) {
            RegExDate regExDate = new RegExDate();
            regExDate.setDatePrefix(pre);
            regExDate.setRegEx(regs);

            regExDates.add(regExDate);
        }
    }

    /**
     * 列举所有可能得日期格式组合
     * @return
     */
    private static List<String> allPureDateRegEx() {
        List<String> regs = new ArrayList<>();
        regs.add("("+regYear+")\\s*-\\s*("+regOther+")\\s*-\\s*(" + regOther + ")");
        regs.add("("+regYear+")\\s*年\\s*("+regOther+")\\s*月\\s*(" + regOther + ")");
        regs.add("("+regYear+")\\s*/\\s*("+regOther+")\\s*/\\s*(" + regOther + ")");

        return regs;
    }

    private static List<RegExDate> regExDates;

    private static String regYear = "\\d{4}";
    private static String regOther = "\\d{2}";
    private static String prefixSpace = "\\s*[:：\\s]\\s*";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LogManager.getLogger(HTMLDateParse.class);
}