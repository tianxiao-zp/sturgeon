package com.sturgeon.remoting.netty;

import org.apache.log4j.Logger;

import com.sturgeon.remoting.api.ChannelHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 心跳检测处理器
 * @author tianxiao
 * @version $Id: IdleHeartBeatHandler.java, v 0.1 2016年12月18日 下午2:35:05 tianxiao Exp $
 */
public class IdleHeartBeatHandler extends IdleStateHandler {

    public IdleHeartBeatHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds,
                                int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    private static Logger logger = Logger.getLogger(IdleHeartBeatHandler.class.getName());

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        super.channelIdle(ctx, evt);
        if (evt.state() == IdleState.WRITER_IDLE) {
            //发送心跳包
            if (logger.isDebugEnabled()) {
                logger.debug("Client heart beat.");
            }
//            Message heartBeatMessage = new Message(new Header(PacketType.HEART_BEAT), new HeartBeat());
//            ctx.writeAndFlush(heartBeatMessage);
        } else if (evt.state() == IdleState.READER_IDLE) {
            //读空闲，则关闭链路
            ctx.close();
        }
    }
}
