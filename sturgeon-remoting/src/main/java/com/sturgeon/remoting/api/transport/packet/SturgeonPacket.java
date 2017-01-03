package com.sturgeon.remoting.api.transport.packet;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.serializable.Serializable;
import com.sturgeon.remoting.api.serializable.SerializableContainer;
import com.sturgeon.remoting.api.serializable.SerializableType;

/**
 * Sturgeon Packet
 * @author tianxiao
 * @version $Id: SturgeonPacket.java, v 0.1 2016年12月25日 下午2:47:04 tianxiao Exp $
 */
public class SturgeonPacket implements Packet {
    private final Serializable serializable;
    
    private SturgeonHeader header;
    
    private byte[] body;
    
    public SturgeonPacket(Header header, byte[] body) {
        SerializableType serializType = SerializableType.valueOf(header.serializableType());
        serializable = SerializableContainer.getSerializable(serializType.getKey(), Constants.DEFAULT_SERIALIZABLE);
        this.body = body;
        if (header instanceof SturgeonHeader) {
            this.header = (SturgeonHeader) header;
            this.header.length(SturgeonHeader.HEADER_LENGTH + this.body.length);
        }
    }
    
    public SturgeonPacket(Header header, Object body) {
        SerializableType serializType = SerializableType.valueOf(header.serializableType());
        serializable = SerializableContainer.getSerializable(serializType.getKey(), Constants.DEFAULT_SERIALIZABLE);
        this.body = serializable.encode(body);
        if (header instanceof SturgeonHeader) {
            this.header = (SturgeonHeader) header;
            this.header.length(SturgeonHeader.HEADER_LENGTH + this.body.length);
        }
    }

    public Header getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public <T> T getBody(Class<T> clazz) {
        return serializable.decode(body, clazz);
    }

}
