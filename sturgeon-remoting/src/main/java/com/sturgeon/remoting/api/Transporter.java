package com.sturgeon.remoting.api;

import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

public interface Transporter {
    
    Server bind(RemotingConfig config, ChannelEventListener listener) throws RemotingException;

    Client connect(RemotingConfig config, ChannelEventListener listener) throws RemotingException;
}
