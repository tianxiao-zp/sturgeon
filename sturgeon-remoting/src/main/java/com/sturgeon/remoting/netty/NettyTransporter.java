package com.sturgeon.remoting.netty;

import com.sturgeon.remoting.api.Client;
import com.sturgeon.remoting.api.Server;
import com.sturgeon.remoting.api.Transporter;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

public class NettyTransporter implements Transporter {

    @Override
    public Server bind(RemotingConfig config,
                       ChannelEventListener listener) throws RemotingException {
        return new NettyServer(config, listener);
    }

    @Override
    public Client connect(RemotingConfig config,
                          ChannelEventListener listener) throws RemotingException {
        return new NettyClient(config, listener);
    }

}
