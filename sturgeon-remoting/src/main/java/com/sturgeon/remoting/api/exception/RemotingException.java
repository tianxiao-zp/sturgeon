package com.sturgeon.remoting.api.exception;

import java.net.InetSocketAddress;

import com.sturgeon.remoting.api.Channel;

public class RemotingException extends Exception {
    /** @author tianxiao 2016年12月9日 上午10:19:29 */
    private static final long serialVersionUID = 5390497763808391459L;
    
    private InetSocketAddress localAddress;

    private InetSocketAddress remoteAddress;

    public RemotingException(String message) {
        super(message);
    }
    
    public RemotingException(Channel channel, String msg){
        this(channel == null ? null : channel.getLocalAddress(), channel == null ? null : channel.getRemoteAddress(),
             msg);
    }
    
    public RemotingException(InetSocketAddress localAddress, InetSocketAddress remoteAddress, String message){
        super(message);

        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }
}
