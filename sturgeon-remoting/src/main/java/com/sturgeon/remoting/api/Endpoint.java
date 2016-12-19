package com.sturgeon.remoting.api;

import java.net.InetSocketAddress;

import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * 终端
 * @author tianxiao
 * @version $Id: Endpoint.java, v 0.1 2016年12月9日 上午10:28:13 tianxiao Exp $
 */
public interface Endpoint {
    /**
     * 获取配置信息
     * @author tianxiao
     * 2016年12月9日 上午10:21:01
     * @return
     */
    RemotingConfig getConfig();

    /**
     * 获取本机地址
     * @author tianxiao
     * 2016年12月9日 上午10:21:12
     * @return
     */
    InetSocketAddress getLocalAddress();
    
    /**
     * 发送消息
     * @author tianxiao
     * 2016年12月9日 上午10:21:25
     * @param message
     * @param sent 是否验证超时 true验证超时，false不验证
     */
    void send(Object message, boolean sent) throws RemotingException;

    /**
     * 关闭
     * @author tianxiao
     * 2016年12月9日 上午10:22:44
     */
    void close();
    
    /**
     * 是否已经关闭
     * @author tianxiao
     * 2016年12月9日 上午10:23:24
     * @return
     */
    boolean isClosed();
    
//    /**
//     * 建立连接
//     * @author tianxiao
//     * 2016年12月9日 上午10:39:35
//     * @throws RemotingException
//     */
//    void connected() throws RemotingException;
//    
//    /**
//     * 取消连接
//     * @author tianxiao
//     * 2016年12月9日 上午10:24:28
//     * @throws RemotingException
//     */
//    void disconnected() throws RemotingException;
}
