package com.sturgeon.remoting.api.codec;

import com.sturgeon.common.utils.ObjectUtils;
import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.io.ChannelBuffer;
import com.sturgeon.remoting.api.serializable.SerializableType;
import com.sturgeon.remoting.api.transport.packet.Header;
import com.sturgeon.remoting.api.transport.packet.Packet;
import com.sturgeon.remoting.api.transport.packet.PacketType;
import com.sturgeon.remoting.api.transport.packet.SturgeonHeader;
import com.sturgeon.remoting.api.transport.packet.SturgeonPacket;

/**
 * SturgeonCodec
 * @author tianxiao
 * @version $Id: SturgeonCodec.java, v 0.1 2016年12月24日 下午6:02:39 tianxiao Exp $
 */
public class SturgeonCodec implements Codec {

    public ChannelBuffer encode(ChannelBuffer out, Channel channel, Object message) {
        if (message instanceof Packet) {
            Packet msg = (Packet) message;
            Header header = msg.getHeader();
            byte[] body = msg.getBody();

            out.writeInt(header.length());
            out.writeShort(header.packetType());
            out.writeShort(header.serializableType());
            out.writeBoolean(header.needReturn());
            out.writeBytes(body);
        }
        return out;
    }

    public Object decode(ChannelBuffer in, Channel channel) {
        Header header = readHeader(in);
        if (ObjectUtils.isNull(header)) {
            return null;
        }
        byte[] body = new byte[header.bodyLength()];
        in.readBytes(body);
        SturgeonPacket sturgeonPacket = new SturgeonPacket(header, body);
        return sturgeonPacket;
    }

    private Header readHeader(ChannelBuffer in) {
        //检测输入byteBuffer，避免分包粘包
        if (in.readableBytes() < SturgeonHeader.HEADER_LENGTH) {
            return null;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            return null;
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return null;
        }
        short serializableType = in.readShort();
        short packetType = in.readShort();
        boolean needReturn = in.readBoolean();
        return SturgeonHeader.builer().length(dataLength).packetType(PacketType.valueOf(packetType))
            .serializableType(SerializableType.valueOf(serializableType)).needReturn(needReturn);
    }

}
