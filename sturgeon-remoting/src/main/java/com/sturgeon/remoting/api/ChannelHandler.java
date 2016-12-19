package com.sturgeon.remoting.api;

public interface ChannelHandler {
    Object getHeartBeatMessage();
    
    void setHeartBeatMessage(Object object);
}
