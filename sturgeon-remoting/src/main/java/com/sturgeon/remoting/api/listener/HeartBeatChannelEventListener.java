package com.sturgeon.remoting.api.listener;

public interface HeartBeatChannelEventListener<T> extends ChannelEventListener {
    T getHearBeatHandler();
}
