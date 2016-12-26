package com.sturgeon.remoting.netty;

import java.util.List;

import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.io.ChannelBuffer;
import com.sturgeon.remoting.api.io.NettyChannelBuffer;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * netty 编解码适配器
 * @author tianxiao
 * @version $Id: NettyCodecAdapter.java, v 0.1 2016年12月5日 下午1:34:53 tianxiao Exp $
 */
final class NettyCodecAdapter {
    
    private final Codec          codec;

    private final RemotingConfig config;

    private final int            bufferSize;

    public NettyCodecAdapter(Codec codec, RemotingConfig config) {
        this.codec = codec;
        this.config = config;
        bufferSize = 1024;
    }

    public ChannelHandler getEncoder() {
        return new NettyEncoder();
    }

    public ChannelHandler getDecoder() {
        return new NettyDecoder();
    }

    public class NettyDecoder extends ByteToMessageDecoder {

        @Override
        public final void decode(ChannelHandlerContext ctx, ByteBuf in,
                                 List<Object> out) throws Exception {
            if (in.readableBytes() > bufferSize) {
                return;
            }
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

    public class NettyEncoder extends MessageToByteEncoder<Packet> {

        @Override
        protected void encode(ChannelHandlerContext ctx, Packet message,
                              ByteBuf out) throws Exception {
            String protocol = config.getProtocol();
            NettyChannel ch = NettyChannel.getOrAddChannel(config, ctx.channel());
            ChannelBuffer channelBuffer = codec.encode(new NettyChannelBuffer(out), ch, message);
            NettyChannelBuffer buffer = null;
            if (channelBuffer instanceof NettyChannelBuffer) {
                buffer = (NettyChannelBuffer) channelBuffer;
                out = buffer.getByteBuf();
            }
        }

    }
}
