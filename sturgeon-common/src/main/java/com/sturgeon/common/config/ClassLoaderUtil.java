package com.sturgeon.common.config;

public class ClassLoaderUtil {
    
    public static String getAbsolutePath(String path) {
        return getClassLoaderPath() + path;
    }
    
    private static String getClassLoaderPath() {
        return ClassLoaderUtil.getClassLoader().getResource("").toString();
    }
    
    public static ClassLoader getClassLoader() {
        return ClassLoaderUtil.class.getClassLoader();
    }
    
    public static void main(String[] args) {
        System.out.println(getAbsolutePath("sturgeon-config.xml"));
    }
}
