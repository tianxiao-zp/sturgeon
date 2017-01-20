package com.sturgeon.remoting.test;

import com.sturgeon.remoting.api.Channel;
import com.sturgeon.remoting.api.Client;
import com.sturgeon.remoting.api.Transporter;
import com.sturgeon.remoting.api.exception.RemotingException;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.serializable.SerializableType;
import com.sturgeon.remoting.api.transport.RemotingConfig;
import com.sturgeon.remoting.api.transport.packet.Header;
import com.sturgeon.remoting.api.transport.packet.Packet;
import com.sturgeon.remoting.api.transport.packet.PacketType;
import com.sturgeon.remoting.api.transport.packet.SturgeonHeader;
import com.sturgeon.remoting.api.transport.packet.SturgeonPacket;
import com.sturgeon.remoting.netty.NettyTransporter;

public class NettyClientTest {
    public static void main(String[] args) throws RemotingException {
        Transporter transporter = new NettyTransporter();
        RemotingConfig config = new RemotingConfig("client", "127.0.0.1", 9999);
        Client connect = transporter.connect(config, new ChannelEventListener() {

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
        Header header = SturgeonHeader.builer().needReturn(false).packetType(PacketType.RPC)
            .serializableType(SerializableType.PROTOBUFFER);
        Packet message = new SturgeonPacket(header, "i'm client");

        for (int i = 0; i < 10; i++) {
            connect.send(message, false);
        }

        while (true) {
        }
    }
}
