package com.sturgeon.common.utils;

/**
 * 对象工具类
 * @author tianxiao
 * @version $Id: ObjectsUtils.java, v 0.1 2016年12月24日 下午2:43:32 tianxiao Exp $
 */
public class ObjectUtils {
    private ObjectUtils() {
        // no need instance
    }
    
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
    
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }
}
