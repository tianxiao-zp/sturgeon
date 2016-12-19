package com.sturgeon.remoting.api.serializable;

/**
 * 序列化接口
 * @author tianxiao
 * @version $Id: Codec.java, v 0.1 2016年12月2日 下午1:48:31 tianxiao Exp $
 */
public interface Serializable {
    
    /**
     * 解码
     * @author tianxiao
     * 2016年12月2日 下午3:01:25
     * @param bytes
     * @param calss
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clz);
    
    /**
     * 编码
     * @author tianxiao
     * 2016年12月2日 下午3:01:48
     * @param object
     * @return
     */
    <T> byte[] encode(T object);
}
