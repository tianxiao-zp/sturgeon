package com.sturgeon.remoting.test;

import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.Transporter;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Packet;
import com.sturgeon.remoting.netty.NettyTransporter;

public class NettyServerTest {
    public static void main(String[] args) throws RemotingException {
        Transporter transporter = new NettyTransporter();
        RemotingConfig config = new RemotingConfig("server", "127.0.0.1", 9999);
        transporter.bind(config, new ChannelEventListener() {
            
            public void onSent(Channel channel, Object message) throws RemotingException {
            }
            
            public void onReceived(Channel channel, Object message) throws RemotingException {
                if (message != null && message instanceof Packet) {
                    Packet msg = (Packet) message;
                    System.out.println(msg.getBody(String.class));
                }
            }
            
            public void onDisconnected(Channel channel) throws RemotingException {
            }
            
            public void onConnected(Channel channel) throws RemotingException {
            }
            
            public void onCaught(Channel channel, Throwable exception) throws RemotingException {
            }
            
            public void onActive(Channel channel) throws RemotingException {
            }
        });
    }
}
