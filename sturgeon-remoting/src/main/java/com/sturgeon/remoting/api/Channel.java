package com.sturgeon.remoting.api;

import java.net.InetSocketAddress;

/**
 * Channel
 * @author tianxiao
 * @version $Id: Channel.java, v 0.1 2016年12月12日 上午11:02:16 tianxiao Exp $
 */
public interface Channel extends Endpoint {
    
    /**
     * 获取连接的地址
     * @author tianxiao
     * 2016年12月12日 上午11:02:57
     * @return
     */
    InetSocketAddress getRemoteAddress();

    /**
     * 是否已经链接上
     * @author tianxiao
     * 2016年12月12日 上午11:03:16
     * @return
     */
    boolean isConnected();

    /**
     * 是否含有参数/属性
     * @author tianxiao
     * 2016年12月12日 上午11:03:38
     * @param key
     * @return
     */
    boolean hasAttribute(String key);

    /**
     * 通过key获取参数
     * @author tianxiao
     * 2016年12月12日 上午11:03:51
     * @param key
     * @return
     */
    Object getAttribute(String key);

    /**
     * 设置参数
     * @author tianxiao
     * 2016年12月12日 上午11:04:23
     * @param key
     * @param value
     */
    void setAttribute(String key,Object value);
    
    /**
     * 移除参数
     * @author tianxiao
     * 2016年12月12日 上午11:04:31
     * @param key
     */
    void removeAttribute(String key);
}

