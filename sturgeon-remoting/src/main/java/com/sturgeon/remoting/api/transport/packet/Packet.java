package com.sturgeon.remoting.api.transport.packet;

public interface Packet {
    Header getHeader();

    byte[] getBody();

    <T> T getBody(Class<T> clazz);
}
