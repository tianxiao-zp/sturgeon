package com.sturgeon.remoting.netty;

import com.sturgeon.common.Constants;
import com.sturgeon.common.utils.NamedThreadFactory;
import com.sturgeon.remoting.api.AbstractClient;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty客户端
 * @author tianxiao
 * @version $Id: NettyClient.java, v 0.1 2016年12月8日 下午5:14:35 tianxiao Exp $
 */
public class NettyClient extends AbstractClient {
    private EventLoopGroup       group;
    private Bootstrap            bootstrap;

    private volatile Channel     channel;

    public NettyClient(RemotingConfig config,
                       ChannelEventListener listener) throws RemotingException {
        super(config, listener);
    }

    @Override
    protected void doOpen() {
        group = new NioEventLoopGroup(Constants.DEFAULT_IO_THREADS,
            new NamedThreadFactory("NettyClientBoss", true));
        bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout());
        final NettyHandler nettyHandle = new NettyHandler(getConfig(), getListener());
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("decoder", new NettyDecoder(getConfig(), getCodec()));
                pipeline.addLast("encoder", new NettyEncoder(getConfig(), getCodec()));
                pipeline.addLast("handler", nettyHandle);
            }
        });
    }

    @Override
    protected void doClose() throws Throwable {
        group.shutdownGracefully();
    }

    @Override
    protected void doConnect() throws Throwable {
        ChannelFuture future = bootstrap.connect(getConfig().getHost(), 9999);
        // awaitUninterruptibly() 等待连接成功
        channel = future.awaitUninterruptibly().channel();
//        Header header = SturgeonHeader.builer().needReturn(false).packetType(PacketType.RPC).serializableType(SerializableType.PROTOBUFFER);
//        SturgeonPacket message = new SturgeonPacket(header, "i'm client");
//        channel.writeAndFlush(message).awaitUninterruptibly();
//        future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);
        future.isSuccess();
//        channel.closeFuture().sync();
    }

    @Override
    public void send(Object message, boolean sent) throws RemotingException {
        com.sturgeon.remoting.api.Channel channel = getChannel();
        if (channel == null || !channel.isConnected()) {
            throw new RemotingException(channel,
                "message can not send, because channel is closed . config:" + getConfig());
        }
        channel.send(message, sent);
    }

    @Override
    protected com.sturgeon.remoting.api.Channel getChannel() {
        Channel c = channel;
        if (c == null || !c.isActive()) {
            return null;
        }
        return NettyChannel.getOrAddChannel(getConfig(), c);
    }
}
