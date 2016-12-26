package com.sturgeon.remoting.api.serializable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sturgeon.common.utils.StringUtils;

/**
 * 序列化容器
 * @author tianxiao
 * @version $Id: SerializableContainer.java, v 0.1 2016年12月25日 下午2:38:22 tianxiao Exp $
 */
public class SerializableContainer {
    private static ConcurrentMap<String, Serializable> serializables = new ConcurrentHashMap<String, Serializable>();
    
    // 后期可换成配置
    static {
        serializables.putIfAbsent("json", new JSONSerializable());
        serializables.putIfAbsent("protobuffer", new ProtobufferSerializable());
    }
    
    public static Serializable getSerializable(String key, String defaultKey) {
        if (StringUtils.isEmpty(key)) {
            key = defaultKey;
        }
        return serializables.get(key);
    }
}
