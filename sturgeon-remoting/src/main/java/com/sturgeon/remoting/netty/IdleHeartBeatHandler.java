package com.sturgeon.remoting.netty;

import org.apache.log4j.Logger;

import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.HeartBeatHandler;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 心跳检测处理器
 * @author tianxiao
 * @version $Id: IdleHeartBeatHandler.java, v 0.1 2016年12月18日 下午2:35:05 tianxiao Exp $
 */
public class IdleHeartBeatHandler extends IdleStateHandler implements HeartBeatHandler {
    private static Logger logger = Logger.getLogger(IdleHeartBeatHandler.class.getName());
    private volatile RemotingConfig config;

    public IdleHeartBeatHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds,
                                int allIdleTimeSeconds, RemotingConfig config) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
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
}
