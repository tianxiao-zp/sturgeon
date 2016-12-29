package com.sturgeon.remoting.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sturgeon.common.utils.NetUtils;
import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.serializable.SerializableType;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Header;
import com.sturgeon.remoting.api.transport.packet.Packet;
import com.sturgeon.remoting.api.transport.packet.PacketType;
import com.sturgeon.remoting.api.transport.packet.SturgeonHeader;
import com.sturgeon.remoting.api.transport.packet.SturgeonPacket;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class NettyHandler extends SimpleChannelInboundHandler<Object> {
    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>(); // <ip:port, channel>
    private final ChannelEventListener listener;
    private volatile RemotingConfig    config;

    public NettyHandler(RemotingConfig config, ChannelEventListener listener) {
        this.listener = listener;
        this.config = config;
    }
    
    public Map<String, Channel> getChannels() {
        return channels;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
        System.out.println(msg);
        String protocol = config.getProtocol();
        if ("server".equals(protocol)) {
            context.writeAndFlush(System.currentTimeMillis() + "\r\n");
        }
        NettyChannel ch = NettyChannel.getOrAddChannel(config, context.channel());
        try {
            listener.onReceived(ch, msg);
        } finally {
            NettyChannel.removeChannelIfDisconnected(context.channel());
        }
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /*
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        String protocol = config.getProtocol();
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
        String protocol = config.getProtocol();
        super.handlerRemoved(ctx);
        NettyChannel.removeChannelIfDisconnected(ctx.channel());
    }

    /*
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        String protocol = config.getProtocol();
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
        String protocol = config.getProtocol();
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            listener.onCaught(ch, cause);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }
    
    /**
     * 自定义事件处理
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
    
    /**
     * channelDisconnected、channelUnbound和channelClosed处理
     * @author tianxiao
     * 2016年12月22日 上午11:36:14
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
     * @return 
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String protocol = config.getProtocol();
        super.channelInactive(ctx);
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        try {
            listener.onDisconnected(ch);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }
    
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
}
