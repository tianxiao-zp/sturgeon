package com.sturgeon.remoting.api;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.sturgeon.common.Constants;
import com.sturgeon.common.utils.NetUtils;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * AbstractClient
 * @author tianxiao
 * @version $Id: AbstractClient.java, v 0.1 2016年12月12日 下午7:03:36 tianxiao Exp $
 */
public abstract class AbstractClient extends AbstractEndpoint implements Client {
    private static final Logger     logger = Logger.getLogger(AbstractClient.class);
    private final InetSocketAddress connectAddress;
    private final int               timeout;
    private final int               reconnect_interval;

    public AbstractClient(RemotingConfig config, ChannelEventListener listener,
                          HeartBeatHandler channelHandler) throws RemotingException {
        super(config, listener);
        this.connectAddress = config.toInetSocketAddress();
        this.reconnect_interval = config.getParameter("reconnectInterval", 2000);
        timeout = getConfig().getPositiveParameter(Constants.TIME_OUT_KEY,
            Constants.DEFAULT_TIME_OUT);
        try {
            doOpen();
        } catch (Throwable t) {
            throw new RemotingException(config.toInetSocketAddress() + "Failed to connect "
                                        + getClass().getSimpleName() + " on " + getConnectAddress()
                                        + ", cause: " + t.getMessage());
        }
    }

    public InetSocketAddress getLocalAddress() {
        Channel channel = getChannel();
        if (channel == null) {
            return InetSocketAddress.createUnresolved(NetUtils.getLogHost(), 0);
        }
        return channel.getLocalAddress();
    }

    public void close() {
        if (logger.isInfoEnabled()) {
            logger.info("Close " + getClass().getSimpleName() + " connect " + getConnectAddress()
                        + ", export " + getLocalAddress());
        }
        try {
            super.close();
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            doClose();
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
    }

    protected abstract void doConnect() throws Throwable;

    protected abstract void doOpen() throws Throwable;

    protected abstract void doClose() throws Throwable;
    
    
    protected abstract Channel getChannel();

    public InetSocketAddress getConnectAddress() {
        return connectAddress;
    }

    public int getReconnect_interval() {
        return reconnect_interval;
    }

    protected int getTimeout() {
        return timeout;
    }
}
