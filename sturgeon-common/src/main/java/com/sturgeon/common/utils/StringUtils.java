package com.sturgeon.common.utils;

/**
 * 字符串工具类
 * @author tianxiao
 * @version $Id: StringUtils.java, v 0.1 2016年12月5日 下午1:47:17 tianxiao Exp $
 */
public class StringUtils {
    private static final String EMPTY = ""; 
    
    private StringUtils() {
        // 工具类，不需要实例化
    }
    
    public static boolean isEmpty(String s) {
        if (s != null && EMPTY.equals(s)) {
            return true;
        }
        return false;
    }
    
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
