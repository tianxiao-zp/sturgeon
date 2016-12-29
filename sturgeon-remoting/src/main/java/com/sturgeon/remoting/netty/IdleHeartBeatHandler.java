package com.sturgeon.remoting.netty;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.listener.HeartBeatChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Header;
import com.sturgeon.remoting.api.transport.packet.Packet;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 心跳检测处理器
 * @author tianxiao
 * @version $Id: IdleHeartBeatHandler.java, v 0.1 2016年12月18日 下午2:35:05 tianxiao Exp $
 */
public class IdleHeartBeatHandler extends IdleStateHandler
                                  implements HeartBeatChannelEventListener<ChannelHandler> {
    private static Logger           logger = Logger.getLogger(IdleHeartBeatHandler.class.getName());
    private volatile RemotingConfig config;
    final ChannelEventListener      listener;
    
    public static ChannelHandler warp(RemotingConfig config, ChannelEventListener listener) {
        return new IdleHeartBeatHandler(config, listener);
    }

    public IdleHeartBeatHandler(RemotingConfig config, ChannelEventListener listener) {
        super(
            config.getPositiveParameter(Constants.READER_IDLE_TIME_KEY,
                Constants.DEFAULT_READER_IDLE_TIME),
            config.getPositiveParameter(Constants.WRITER_IDLE_TIME_KEY,
                Constants.DEFAULT_WRITER_IDLE_TIME),
            config.getPositiveParameter(Constants.ALL_IDLE_TIME_KEY,
                Constants.DEFAULT_ALL_IDEL_TIME),
            TimeUnit.SECONDS);
        this.config = config;
        this.listener = listener;
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        super.channelIdle(ctx, evt);
        if (evt.state() == IdleState.WRITER_IDLE) {
            //发送心跳包
            if (logger.isDebugEnabled()) {
                logger.debug("Client heart beat.");
            }
            NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
            //心跳暂时发送空消息
            sendHeartBeat(ch, new Packet() {
                
                public Header getHeader() {
                    return null;
                }
                
                public <T> T getBody(Class<T> clazz) {
                    return null;
                }
                
                public byte[] getBody() {
                    return null;
                }
            });
        } else if (evt.state() == IdleState.READER_IDLE) {
            //读空闲，则关闭链路
            ctx.close();
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    public RemotingConfig getRemotingConfig() {
        return this.config;
    }

    public void sendHeartBeat(Channel channel, Object message) throws RemotingException {
        channel.send(message, false);
    }

    public void onActive(Channel channel) throws RemotingException {
        listener.onActive(channel);
    }

    public void onConnected(Channel channel) throws RemotingException {
        listener.onConnected(channel);
    }

    public void onDisconnected(Channel channel) throws RemotingException {
        listener.onDisconnected(channel);
    }

    public void onSent(Channel channel, Object message) throws RemotingException {
        listener.onSent(channel, message);
    }

    public void onReceived(Channel channel, Object message) throws RemotingException {
        listener.onReceived(channel, message);
    }

    public void onCaught(Channel channel, Throwable exception) throws RemotingException {
        listener.onCaught(channel, exception);
    }

    public ChannelHandler getHearBeatHandler() {
        return this;
    }
}
