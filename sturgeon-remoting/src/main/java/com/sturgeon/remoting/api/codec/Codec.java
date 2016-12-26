package com.sturgeon.remoting.api.codec;

import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.io.ChannelBuffer;

/**
 * 传输层编解码器
 * @author tianxiao
 * @version $Id: Codec.java, v 0.1 2016年12月5日 上午10:23:15 tianxiao Exp $
 */
public interface Codec {
    
    /**
     * 编码
     * @author tianxiao
     * 2016年12月5日 上午11:02:00
     * @param buffer
     * @param message
     */
    ChannelBuffer encode(ChannelBuffer buffer,Channel channel, Object message);
    
    /**
     * 解码
     * @author tianxiao
     * 2016年12月5日 上午11:02:10
     * @param buffer
     * @return
     */
    Object decode(ChannelBuffer buffer, Channel channel);
}
