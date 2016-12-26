package com.sturgeon.remoting.api.serializable;

/**
 * 序列化工具类型
 * @author tianxiao
 * @version $Id: SerializableType.java, v 0.1 2016年12月25日 下午2:40:44 tianxiao Exp $
 */
public enum SerializableType {
                              /**PROTOBUFFER  @author tianxiao 2016年12月24日 下午4:25:33 */
                              PROTOBUFFER((short) 1, "protobuffer"),
                              /**JSON @author tianxiao 2016年12月24日 下午4:25:43 */
                              JSON((short) 2, "json");

    private short  value;

    private String key;

    private SerializableType(short value, String key) {
        this.value = value;
        this.key = key;
    }

    public short getValue() {
        return value;
    }
    
    public String getKey() {
        return key;
    }

    public static SerializableType valueOf(short value) {
        SerializableType[] values = values();
        for (SerializableType v : values) {
            if (value == v.getValue()) {
                return v;
            }
        }
        return null;
    }
}
