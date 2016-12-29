package com.sturgeon.remoting.netty;
import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.io.ChannelBuffer;
import com.sturgeon.remoting.api.io.NettyChannelBuffer;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<Object> {
    private final RemotingConfig config;
    
    private final Codec codec;
    
    public NettyEncoder(RemotingConfig config, Codec code) {
        this.config = config;
        this.codec = code;
    }

        @Override
        protected void encode(ChannelHandlerContext ctx, Object message,
                              ByteBuf out) throws Exception {
            NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
            ChannelBuffer channelBuffer = codec.encode(new NettyChannelBuffer(out), ch, message);
            NettyChannelBuffer buffer = null;
            if (channelBuffer instanceof NettyChannelBuffer) {
                buffer = (NettyChannelBuffer) channelBuffer;
                out = buffer.getByteBuf();
            }
        }

    }