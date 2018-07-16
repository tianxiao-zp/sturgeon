package com.sturgeon.remoting.api.serializable;

import com.alibaba.fastjson.JSON;

public class JSONSerializable implements Serializable {

    @Override
    public <T> T decode(byte[] buffer, Class<T> clz) {
        if (buffer == null || buffer.length == 0) {
            return null;
        }
        String body = new String(buffer);
        return JSON.parseObject(body, clz);
    }

    @Override
    public <T> byte[] encode(T object) {
        if (object == null) {
            return null;
        }
        String obj = JSON.toJSONString(object);
        return obj.getBytes();
    }
}
