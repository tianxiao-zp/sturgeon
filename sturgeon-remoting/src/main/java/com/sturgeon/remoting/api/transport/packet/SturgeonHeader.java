package com.sturgeon.remoting.api.transport.packet;

import com.sturgeon.remoting.api.serializable.SerializableType;

/**
 * SturgeonHeader
 * @author tianxiao
 * @version $Id: SturgeonHeader.java, v 0.1 2016年12月24日 下午5:38:15 tianxiao Exp $
 */
public class SturgeonHeader implements Header {

    public final static short HEADER_LENGTH = 9;

    private PacketType        packetType;

    private boolean           needReturn;

    private SerializableType  serializableType;

    private int               length;
    
    private SturgeonHeader() {
        
    }

    public short serializableType() {
        return serializableType.getValue();
    }

    public int length() {
        return this.length;
    }

    public short packetType() {
        return packetType.getValue();
    }

    public boolean needReturn() {
        return needReturn;
    }

    public void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }

    public void setNeedReturn(boolean needReturn) {
        this.needReturn = needReturn;
    }

    public int headLength() {
        return HEADER_LENGTH;
    }

    public int bodyLength() {
        return length() - headLength();
    }

    public static SturgeonHeader builer() {
        return new SturgeonHeader();
    }

    public SturgeonHeader packetType(PacketType packetTyp) {
        this.packetType = packetTyp;
        return this;
    }

    public SturgeonHeader serializableType(SerializableType serializableType) {
        this.serializableType = serializableType;
        return this;
    }

    public SturgeonHeader needReturn(boolean needReturn) {
        this.needReturn = needReturn;
        return this;
    }

    public SturgeonHeader length(int length) {
        this.length = length;
        return this;
    }
}
