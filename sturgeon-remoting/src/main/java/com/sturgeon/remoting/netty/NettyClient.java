package com.sturgeon.remoting.netty;

import com.sturgeon.common.Constants;
import com.sturgeon.common.utils.NamedThreadFactory;
import com.sturgeon.remoting.api.AbstractClient;
import com.sturgeon.remoting.api.codec.Codec;
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
    private final RemotingConfig    config;
    private EventLoopGroup          group;
    private Bootstrap               bootstrap;
    private Codec                   codec;

    private volatile Channel        channel;

    public NettyClient(RemotingConfig config,
                       ChannelEventListener listener, com.sturgeon.remoting.api.HeartBeatHandler channelHandler) throws RemotingException {
        super(config, listener, channelHandler);
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
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            NettyCodecAdapter adapter = new NettyCodecAdapter(codec, config);

            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                NettyHandle nettyHandle = new NettyHandle(getConfig(), getListener());
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
        ChannelFuture connect = bootstrap.connect(getConnectAddress());
        channel = connect.channel();
    }

    public void send(Object message, boolean sent) throws RemotingException {
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
