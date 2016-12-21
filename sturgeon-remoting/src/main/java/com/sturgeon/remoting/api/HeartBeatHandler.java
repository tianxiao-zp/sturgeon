package com.sturgeon.remoting.api;

import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * 心跳接口
 * @author tianxiao
 * @version $Id: HeartBeatHandler.java, v 0.1 2016年12月19日 下午9:13:33 tianxiao Exp $
 */
public interface HeartBeatHandler {
    
    /**
     * 获取配置
     * @author tianxiao
     * 2016年12月19日 下午9:16:12
     * @return
     */
    RemotingConfig getRemotingConfig();
    
    /**
     * 发送心跳消息
     * @author tianxiao
     * 2016年12月19日 下午9:13:59
     * @param message
     */
    void sendHeartBeat(Channel channel, Object message)throws RemotingException;
}
