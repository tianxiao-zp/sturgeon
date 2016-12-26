package com.sturgeon.remoting.api.transport.packet;

/**
 * 请求头信息
 * @author tianxiao
 * @version $Id: Header.java, v 0.1 2016年12月5日 上午9:39:29 tianxiao Exp $
 */
public interface Header {
    /**
     * 请求头的总长度
     * @author tianxiao
     * 2016年12月5日 上午9:39:25
     * @return
     */
    int length();
    
    /**头长度
     * @author tianxiao
     * 2016年12月24日 下午4:40:23
     * @return
     */
    int headLength();
    
    /**
     * 消息体长度
     * @author tianxiao
     * 2016年12月24日 下午5:53:27
     * @return
     */
    int bodyLength();
    
    /**
     * 数据包的类型
     * @see com.sturgeon.remoting.api.transport.packet.PacketType  (参考)
     * @author tianxiao
     * 2016年12月24日 下午4:23:28
     * @return
     */
    short packetType();
    
    /**
     * 是否需要返回数据
     * @author tianxiao
     * 2016年12月24日 下午4:24:19
     * @return
     */
    boolean needReturn();
    
    /**
     * 序列化方式
     * @author tianxiao
     * 2016年12月24日 下午4:24:37
     * @return
     */
    short serializableType();
}
