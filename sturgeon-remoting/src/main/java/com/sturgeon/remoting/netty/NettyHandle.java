package com.sturgeon.remoting.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sturgeon.common.utils.NetUtils;
import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@Sharable
public class NettyHandle extends SimpleChannelInboundHandler<Object> {
    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>
    private final ChannelEventListener listener;
    private volatile RemotingConfig    config;

    public NettyHandle(RemotingConfig config, ChannelEventListener listener) {
        this.listener = listener;
        this.config = config;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
        NettyChannel ch = NettyChannel.getOrAddChannel(config, context.channel());
        try {
            listener.onReceived(ch, msg);
        } finally {
            NettyChannel.removeChannelIfDisconnected(context.channel());
        }
    }

    /*
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            channels.put(NetUtils.toAddressString(ch.getLocalAddress()), ch);
            listener.onConnected(ch);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }

    }

    /*
     * .覆盖了 handlerRemoved() 事件处理方法。
     * 每当从服务端收到客户端断开时
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            listener.onDisconnected(ch);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    /*
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            listener.onActive(ch);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    /*
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            listener.onCaught(ch, cause);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }
    
    /**
     * 超时时触发该方法
     * @author tianxiao
     * 2016年12月18日 下午3:54:32
     * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     * @return 
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
//            IdleStateEvent event = (IdleStateEvent) evt;
//            if (event.state() == IdleState.READER_IDLE)
//                System.out.println("read idle");
//            else if (event.state() == IdleState.WRITER_IDLE)
//                System.out.println("write idle");
//            else if (event.state() == IdleState.ALL_IDLE)
//                System.out.println("all idle");
//        }
    }
}
