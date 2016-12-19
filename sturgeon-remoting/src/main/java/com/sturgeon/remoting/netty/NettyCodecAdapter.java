package com.sturgeon.remoting.netty;

import java.util.List;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.io.NettyChannelBuffer;
import com.sturgeon.remoting.api.transport.RemotingConfig;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * netty 编解码适配器
 * @author tianxiao
 * @version $Id: NettyCodecAdapter.java, v 0.1 2016年12月5日 下午1:34:53 tianxiao Exp $
 */
final class NettyCodecAdapter {
    private ChannelHandler       decoder = new NettyDecoder();

    private ChannelHandler       encoder = new NettyEncoder();

    private final Codec          codec;

    private final RemotingConfig config;

    private final int            bufferSize;

    public NettyCodecAdapter(Codec codec, RemotingConfig config) {
        this.codec = codec;
        this.config = config;
        bufferSize = 1024;
    }

    public ChannelHandler getEncoder() {
        return encoder;
    }

    public ChannelHandler getDecoder() {
        return decoder;
    }

    private class NettyDecoder extends ByteToMessageDecoder {

        public NettyDecoder() {
        }

        @Override
        public final void decode(ChannelHandlerContext ctx, ByteBuf in,
                                 List<Object> out) throws Exception {
            //检测输入byteBuffer，避免分包粘包
            if (in.readableBytes() < Constants.MIN_BUFFER_SIZE) {
                return;
            }
            in.markReaderIndex();
            int dataLength = in.readInt();
            if (dataLength < 0) {
                ctx.close();
            }
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
                return;
            }
            Object message = codec.decode(new NettyChannelBuffer(in));
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

    private class NettyEncoder extends MessageToByteEncoder<Object> {

        @Override
        protected void encode(ChannelHandlerContext paramChannelHandlerContext, Object message,
                              ByteBuf paramByteBuf) throws Exception {
            codec.encode(new NettyChannelBuffer(paramByteBuf), message);
        }

    }
}
