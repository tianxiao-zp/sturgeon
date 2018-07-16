package com.sturgeon.common.config.server;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.sturgeon.common.config.SturgeonRPCConfig;

public class NettyConfig extends SturgeonRPCConfig {
    private static Logger logger = LoggerFactory.getLogger(NettyConfig.class);
    private int messageHeadLength;
    
    private static NettyConfig nettyConfig;
    
    private NettyConfig() {
        super();
        messageHeadLength = getInt("/sturgeon/netty/message/head-length");
    }
    
    public static NettyConfig getInstance() {
        if (nettyConfig == null) {
            synchronized (NettyConfig.class) {
                if (nettyConfig == null) {
                    nettyConfig = new NettyConfig();
                }
            }
        }
        return nettyConfig;
    }
    
    public int getMessageHeadLength() {
        return messageHeadLength;
    }
}
