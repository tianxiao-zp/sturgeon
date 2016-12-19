package com.sturgeon.remoting.api.transport.packet;

/**
 * 请求头信息
 * @author tianxiao
 * @version $Id: Header.java, v 0.1 2016年12月5日 上午9:39:29 tianxiao Exp $
 */
public interface Header {
    /**
     * 请求头的总长度
     * @author tianxiao
     * 2016年12月5日 上午9:39:25
     * @return
     */
    short getLength();
}
