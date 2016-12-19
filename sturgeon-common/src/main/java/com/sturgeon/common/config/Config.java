package com.sturgeon.common.config;

import java.net.URL;

public interface Config {
    
    /**
     * 获取资源路径
     * @author tianxiao
     * 2016年11月11日 下午2:17:32
     * @return
     */
    public String getPath();
    
    /**获取资源配置
     * @author tianxiao
     * 2016年11月11日 下午2:18:46
     * @return
     */
    public URL getResource();
}
