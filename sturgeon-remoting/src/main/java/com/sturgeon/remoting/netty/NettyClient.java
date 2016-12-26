package com.sturgeon.remoting.netty;

import java.util.concurrent.TimeUnit;

import com.sturgeon.common.Constants;
import com.sturgeon.common.utils.NamedThreadFactory;
import com.sturgeon.remoting.api.AbstractClient;
import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Packet;

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
    private final RemotingConfig config;
    private EventLoopGroup       group;
    private Bootstrap            bootstrap;
    private Codec                codec;

    private volatile Channel     channel;

    public NettyClient(RemotingConfig config,
                       ChannelEventListener listener) throws RemotingException {
        super(config, listener);
        this.config = config;
        this.codec = getCodec();
    }

    protected void doOpen() {
        group = new NioEventLoopGroup(Constants.DEFAULT_IO_THREADS,
            new NamedThreadFactory("NettyClientBoss", true));
        bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout());
        final NettyHandler nettyHandle = new NettyHandler(getConfig(), getListener());
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            NettyCodecAdapter adapter = new NettyCodecAdapter(codec, config);

            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("decoder", adapter.getDecoder());
                pipeline.addLast("encoder", adapter.getEncoder());
                pipeline.addLast("handler", nettyHandle);
            }
        });
    }

    public void reconnect() throws RemotingException {
    }

    @Override
    protected void doClose() throws Throwable {
        group.shutdownGracefully();
    }

    @Override
    protected void doConnect() throws Throwable {
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9999);
        channel = future.channel();
        future.awaitUninterruptibly(3000, TimeUnit.MILLISECONDS);
        boolean success = future.isSuccess();
        System.out.println(success);
        channel.closeFuture().sync();
    }

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
