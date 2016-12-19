package com.sturgeon.remoting.netty;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.AbstractServer;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.netty.utils.NettyThreadFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer extends AbstractServer {
    private Logger               logger = Logger.getLogger(NettyServer.class);

    private Map<String, Channel> channels;                                    // <ip:port, channel>

    EventLoopGroup               bossGroup;

    EventLoopGroup               workerGroup;

    private Channel              channel;

    public NettyServer(RemotingConfig config, ChannelEventListener listener) throws RemotingException {
        super(config, listener);
    }

    @Override
    protected void doOpen() {
        bossGroup = new NioEventLoopGroup(getConfig().getPositiveParameter(Constants.IO_THREADS_KEY,
            Constants.DEFAULT_IO_THREADS), new NettyThreadFactory("NettyServerBoss"));
        workerGroup = new NioEventLoopGroup(getConfig()
            .getPositiveParameter(Constants.IO_THREADS_KEY, Constants.DEFAULT_IO_THREADS),
            new NettyThreadFactory("NettyServerWorker"));
        // serverBootstrap 启动NIO服务端的辅助启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 256).option(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            NettyCodecAdapter adapeter = new NettyCodecAdapter(getCodec(), getConfig());

            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                NettyHandle nettyHandle = new NettyHandle(getConfig(), getListener());
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("decoder", adapeter.getDecoder());
                pipeline.addLast("encoder", adapeter.getEncoder());
                pipeline.addLast("handler", nettyHandle);
            }
        });
        try {
            ChannelFuture future = serverBootstrap.bind(getBindAddress()).sync();
            channel = future.channel();
        } catch (InterruptedException e) {
            logger.error("", e);
        }
    }

    @Override
    protected void doClose() throws Throwable {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (Throwable a) {
            logger.warn(a.getMessage(), a);
        }
        try {
            Collection<com.sturgeon.remoting.api.Channel> channels = getChannels();
            for (com.sturgeon.remoting.api.Channel channel : channels) {
                try {
                    channel.close();
                } catch (Throwable a) {
                    logger.warn(a.getMessage(), a);
                }
            }
        } catch (Throwable a) {
            logger.warn(a.getMessage(), a);
        }
        try {
            if (bossGroup != null && workerGroup != null) {
                // release external resource.
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            if (channels != null) {
                channels.clear();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public boolean isBound() {
        if (channel == null) {
            return false;
        }
        return channel.isOpen();
    }

    public Collection<com.sturgeon.remoting.api.Channel> getChannels() {
        return null;
    }

    public void send(Object message, boolean sent) throws RemotingException {
        Collection<com.sturgeon.remoting.api.Channel> channels = getChannels();
        for (com.sturgeon.remoting.api.Channel channel : channels) {
            if (channel.isConnected()) {
                channel.send(message, sent);
            }
        }
    }
}