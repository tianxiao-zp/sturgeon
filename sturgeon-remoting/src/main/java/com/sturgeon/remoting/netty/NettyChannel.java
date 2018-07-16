package com.sturgeon.remoting.netty;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.channel.ChannelFuture;

/**
 * netty channel cantainer
 * @author tianxiao
 * @version $Id: NettyChannel.java, v 0.1 2016年12月21日 下午7:01:45 tianxiao Exp $
 */
public final class NettyChannel implements Channel {
    private io.netty.channel.Channel                                           channel;

    private volatile RemotingConfig                                            config;

    private volatile boolean                                                   closed;

    private final static ConcurrentMap<io.netty.channel.Channel, NettyChannel> channels   = new ConcurrentHashMap<io.netty.channel.Channel, NettyChannel>();

    private final Map<String, Object>                                          attributes = new ConcurrentHashMap<String, Object>();

    public NettyChannel(RemotingConfig config, io.netty.channel.Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("netty channel == null;");
        }
        if (config == null) {
            throw new IllegalArgumentException("netty config == null");
        }
        this.channel = channel;
        this.config = config;
    }

    static NettyChannel getOrAddChannel(RemotingConfig config, io.netty.channel.Channel channel) {
        if (channel == null) {
            return null;
        }
        NettyChannel nettyChannel = channels.get(channel);
        if (nettyChannel == null) {
            NettyChannel ch = new NettyChannel(config, channel);
            if (channel.isOpen()) {
                nettyChannel = channels.putIfAbsent(channel, ch);
            }
            if (nettyChannel == null) {
                nettyChannel = ch;
            }
        }
        return nettyChannel;
    }

    static void removeChannelIfDisconnected(io.netty.channel.Channel ch) {
        if (ch != null && !ch.isOpen()) {
            channels.remove(ch);
        }
    }

    @Override
    public RemotingConfig getConfig() {
        return config;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        SocketAddress localAddress = channel.localAddress();
        if (localAddress == null) {
            localAddress = config.toInetSocketAddress();
        }
        return (InetSocketAddress) localAddress;
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) channel.remoteAddress();
    }

    @Override
    public boolean isConnected() {
        return channel.isOpen() && channel.isActive();
    }

    @Override
    public boolean hasAttribute(String key) {
        return attributes.containsKey(key);
    }

    @Override
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        /**
         * The null value unallowed in the ConcurrentHashMap.
         */
        if (value == null) {
            attributes.remove(key);
        } else {
            attributes.put(key, value);
        }
    }

    @Override
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NettyChannel other = (NettyChannel) obj;
        if (channel == null) {
            if (other.channel != null)
                return false;
        } else if (!channel.equals(other.channel))
            return false;
        return true;
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        if (isClosed()) {
            throw new RemotingException("channel:" + getLocalAddress() + " is closed");
        }
        ChannelFuture future = channel.writeAndFlush(message);
        boolean success = true;
        int timeout = 0;
        try {
            if (sent) {
                timeout = getConfig().getPositiveParameter(Constants.TIME_OUT_KEY,
                    Constants.DEFAULT_TIME_OUT);

                success = future.await(timeout);
                Throwable cause = future.cause();
                if (cause != null) {
                    throw cause;
                }
            }
        } catch (Throwable e) {
            throw new RemotingException(this, "Failed to send message " + message + " to "
                                              + getRemoteAddress() + ", cause: " + e.getMessage());
        }
        if (!success) {
            throw new RemotingException(this,
                "Failed to send message " + message + " to " + getRemoteAddress() + "in timeout("
                                              + timeout + "ms) limit");
        }
    }
}
