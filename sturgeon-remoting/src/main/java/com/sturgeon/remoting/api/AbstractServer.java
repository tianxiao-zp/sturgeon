package com.sturgeon.remoting.api;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * AbstractServer
 * @author tianxiao
 * @version $Id: AbstractServer.java, v 0.1 2016年12月9日 上午11:17:24 tianxiao Exp $
 */
public abstract class AbstractServer extends AbstractEndpoint implements Server {
    private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);
    private InetSocketAddress localAddress;
    
    private InetSocketAddress bindAddress;

    public AbstractServer(RemotingConfig config, ChannelEventListener listener) throws RemotingException {
        super(config, listener);
        localAddress = getConfig().toInetSocketAddress();
        // TODO 暂时让绑定的地址等于本机
        bindAddress = localAddress;
        try {
            doOpen();
        } catch (Throwable t) {
            throw new RemotingException(config.toInetSocketAddress() + "Failed to bind " + getClass().getSimpleName() 
                + " on " + getLocalAddress() + ", cause: " + t.getMessage());
        }
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }
    
    public InetSocketAddress getBindAddress() {
        return bindAddress;
    }

    protected abstract void doOpen() throws Throwable;
    
    protected abstract void doClose() throws Throwable;

    public void close() {
        if (logger.isInfoEnabled()) {
            logger.info("Close " + getClass().getSimpleName() + " bind " + getBindAddress() + ", export " + getLocalAddress());
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

    public void close(int timeout) {
    }
}
