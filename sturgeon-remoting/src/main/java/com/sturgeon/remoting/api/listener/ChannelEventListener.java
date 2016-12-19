package com.sturgeon.remoting.api.listener;

import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.exception.RemotingException;

/**
 * channel事件监听器
 * @author tianxiao
 * @version $Id: ChannelEventListener.java, v 0.1 2016年12月12日 上午9:40:39 tianxiao Exp $
 */
public interface ChannelEventListener extends Listener {
    
    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:14
     * @param channel
     * @throws RemotingException
     */
    void onActive(Channel channel) throws RemotingException;
    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:19
     * @param channel
     * @throws RemotingException
     */
    void onConnected(Channel channel) throws RemotingException;

    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:25
     * @param channel
     * @throws RemotingException
     */
    void onDisconnected(Channel channel) throws RemotingException;


    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:32
     * @param channel
     * @param message
     * @throws RemotingException
     */
    void onSent(Channel channel, Object message) throws RemotingException;

    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:37
     * @param channel
     * @param message
     * @throws RemotingException
     */
    void onReceived(Channel channel, Object message) throws RemotingException;

    /**
     * @author tianxiao
     * 2016年12月18日 下午2:22:45
     * @param channel
     * @param exception
     * @throws RemotingException
     */
    void onCaught(Channel channel, Throwable exception) throws RemotingException;
}
