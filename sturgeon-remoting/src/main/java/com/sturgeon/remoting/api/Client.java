package com.sturgeon.remoting.api;

import com.sturgeon.remoting.api.exception.RemotingException;

/**
 * 客户端接口
 * @author tianxiao
 * @version $Id: Client.java, v 0.1 2016年12月9日 上午10:17:26 tianxiao Exp $
 */
public interface Client extends Endpoint {
    /**
     * 重新连接
     * @author tianxiao
     * 2016年12月9日 上午10:24:38
     * @throws RemotingException
     */
    void reconnect()throws RemotingException;
}
