package com.hongru.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class StrUtils {
	public static final String DEFAULT_STRING = "";
	public static final int DEFAULT_INT = 0;
	public static final long DEFAULT_LONG = 0l;
	public static final double DEFAULT_DOUBLE = 0.00;
	
	public static String getString(HttpServletRequest request, String s) {
		return getString(request, s, DEFAULT_STRING); 
	}
	
	public static String getString(HttpServletRequest request, String s,
			String defaultString) {
		String s1 = request.getParameter(s);
		if (StringUtils.isBlank(s1))
			return defaultString;
		return XssUtil.xssChange(s1);
	}
	
	public static int getInt(HttpServletRequest request, String s){
		return getInt(request, s, DEFAULT_INT);
	}
	
	public static int getInt(HttpServletRequest request, String s,
			int defaultInt) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultInt;
			else
				return Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static long getLong(HttpServletRequest request, String s,
			long defaultLong) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultLong;
			else
				return Long.parseLong(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static long getLong(HttpServletRequest request, String s){
		return getLong(request, s, DEFAULT_LONG);
	}
	
	public static double getDouble(HttpServletRequest request, String s,
			double defaultLong) {
		try {
			String temp = getString(request, s);
			if (StringUtils.isBlank(temp))
				return defaultLong;
			else
				return Double.parseDouble(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static double getDouble(HttpServletRequest request, String s){
		return getDouble(request, s, DEFAULT_DOUBLE);
	}
	
	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String s1 = request.getRemoteAddr();
		if (s1 == null)
			return "";
		return s1;
	}
	
	/**
	 * 生成MD5 32位
	 * @param inStr
	 * @return
	 */
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}
	
	/**
     * 生成签名
     * @param params
     * @param psk
     * @return
     */
    public static String generateSign1(Map<String, String> params, String psk) {
        Map<String, String> data = new TreeMap<String, String>();
        data.putAll(params);
        data.remove("sign");
        StringBuilder buffer = new StringBuilder();
        for (String k : data.keySet()) {
            String v = data.get(k);
            if (StringUtils.isNotEmpty(v)) {
                v = v.replaceAll("\\s", "") ;
                try {
                    buffer.append(k + "=" + URLEncoder.encode(v, "UTF-8") + "&");
                } catch (UnsupportedEncodingException e) {
                    //swallow e. IMP
                }
            }
        }
        buffer.append(psk);
        return StrUtils.MD5(buffer.toString());
    }

	/**
     * 生成签名-new
     * @param params
     * @param psk
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String generateSign(Map<String, String> params, String psk) {
        Map<String, String> data = new TreeMap<String, String>();
        data.putAll(params);
        data.remove("sign");
        StringBuilder buffer = new StringBuilder();
        for (String k : data.keySet()) {
            String v = data.get(k);
            if (StringUtils.isNotEmpty(v)) {
                buffer.append(k + "=" + v + "&");
            }
        }
        buffer.append("key=" + psk);
        return Md5Encrypt.md5(buffer.toString());
    }
    
    /**
     * 获取百分比
     * @param a1
     * @param a2
     * @return
     */
    public static int complete(String a1,int a2){
    	double a = Double.parseDouble(a1) / a2 * 100;
    	return (int)Math.ceil(a);
    }
    
    /**
     * 截取字符串
     * @param str
     * @param subnum
     * @return
     */
    public static String subString(String str,int subnum){
    	String reStr = "";
    	if(str.length() <= subnum){
    		reStr = str;
    	} else{
    		reStr = str.substring(0, subnum) + "...";
    	}
    	return reStr;
    }
    
    public static void main(String[] a){
    	String str = "lepay_order_no=14320151225894297982935041&merchant_business_id=20&merchant_no=201512250160158816&out_trade_no=201512250160158816&paytime=2015-12-25 13:56:50&price=0.01&product_id=201510250825947174&sign_type=MD5&trade_result=success&userid=85525519&version=1.0&key=b3863b97bf9c048948dee589942366c6";
    	System.out.println(StrUtils.MD5(str));
    	System.out.println(Md5Encrypt.md5(str));
    }

}
