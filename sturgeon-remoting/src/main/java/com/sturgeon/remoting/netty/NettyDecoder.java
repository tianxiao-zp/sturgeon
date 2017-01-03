package com.sturgeon.remoting.netty;

import java.util.List;

import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.io.NettyChannelBuffer;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class NettyDecoder extends ByteToMessageDecoder {
    private final RemotingConfig config;

    private final Codec          codec;

    public NettyDecoder(RemotingConfig config, Codec code) {
        this.config = config;
        this.codec = code;
    }

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in,
                             List<Object> out) throws Exception {
        NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
        Object message = codec.decode(new NettyChannelBuffer(in), ch);
        if (message == null) {
            return;
        }
        out.add(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
