package com.sturgeon.remoting.api.transport.packet;

/**
 * 数据包类型
 * @author tianxiao
 * @version $Id: PacketType.java, v 0.1 2016年12月24日 下午4:25:21 tianxiao Exp $
 */
public enum PacketType {
                        /**心跳包  @author tianxiao 2016年12月24日 下午4:25:33 */
                        HEART_BEAT((short) 1), 
                        /**RPC包 @author tianxiao 2016年12月24日 下午4:25:43 */
                        RPC((short) 2), 
                        /**注册包 @author tianxiao 2016年12月24日 下午4:26:03 */
                        REGISTRY((short) 3);

    private short value;

    private PacketType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
    
    public static PacketType valueOf(Short value) {
        PacketType[] values = values();
        for (PacketType packetType : values) {
            if (value == packetType.getValue()) {
                return packetType;
            }
        }
        return null;
    }
}
