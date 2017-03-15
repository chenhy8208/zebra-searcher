package com.hongru.common.util;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

public class XssUtil {
	/**
	 * 对url传过来的参数进行过滤
	 * @param url
	 * @return
	 */
	public static String xssChange(String str){
		if(null != str){
			str = JavaScriptUtils.javaScriptEscape(str);
			str = HtmlUtils.htmlEscape(str);
		}
		return str;
	}
	
	/**
	 * 对页面上显示url的进行过滤。否则会有xss进行攻击
	 * @param str
	 * @return
	 */
	public static String xssUrlChange(String str){
		if(null != str){
			str = java.net.URLEncoder.encode(str);
		}
		return str;
	}
}