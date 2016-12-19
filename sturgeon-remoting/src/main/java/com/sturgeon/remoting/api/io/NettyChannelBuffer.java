package com.sturgeon.remoting.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;

public class NettyChannelBuffer implements ChannelBuffer {
    
    private final io.netty.buffer.ByteBuf byteBuf;
    
    public NettyChannelBuffer(io.netty.buffer.ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public int capacity() {
        return byteBuf.capacity();
    }

    public ChannelBuffer capacity(int paramInt) {
        this.byteBuf.capacity(paramInt);
        return this;
    }

    public int maxCapacity() {
        return this.byteBuf.maxCapacity();
    }

    public ChannelBuffer order(ByteOrder paramByteOrder) {
        this.byteBuf.order(paramByteOrder);
        return this;
    }

    public ChannelBuffer unwrap() {
        this.byteBuf.unwrap();
        return this;
    }

    public boolean isDirect() {
        return this.byteBuf.isDirect();
    }

    public int readerIndex() {
        return 0;
    }

    public ChannelBuffer readerIndex(int paramInt) {
        return null;
    }

    public int writerIndex() {
        return 0;
    }

    public ChannelBuffer writerIndex(int paramInt) {
        return null;
    }

    public ChannelBuffer setIndex(int paramInt1, int paramInt2) {
        return null;
    }

    public int readableBytes() {
        return 0;
    }

    public int writableBytes() {
        return 0;
    }

    public int maxWritableBytes() {
        return 0;
    }

    public boolean isReadable() {
        return false;
    }

    public boolean isReadable(int paramInt) {
        return false;
    }

    public boolean isWritable() {
        return false;
    }

    public boolean isWritable(int paramInt) {
        return false;
    }

    public ChannelBuffer clear() {
        return null;
    }

    public ChannelBuffer markReaderIndex() {
        return null;
    }

    public ChannelBuffer resetReaderIndex() {
        return null;
    }

    public ChannelBuffer markWriterIndex() {
        return null;
    }

    public ChannelBuffer resetWriterIndex() {
        return null;
    }

    public ChannelBuffer discardReadBytes() {
        return null;
    }

    public ChannelBuffer discardSomeReadBytes() {
        return null;
    }

    public ChannelBuffer ensureWritable(int paramInt) {
        return null;
    }

    public int ensureWritable(int paramInt, boolean paramBoolean) {
        return 0;
    }

    public boolean getBoolean(int paramInt) {
        return false;
    }

    public byte getByte(int paramInt) {
        return 0;
    }

    public short getUnsignedByte(int paramInt) {
        return 0;
    }

    public short getShort(int paramInt) {
        return 0;
    }

    public int getUnsignedShort(int paramInt) {
        return 0;
    }

    public int getMedium(int paramInt) {
        return 0;
    }

    public int getUnsignedMedium(int paramInt) {
        return 0;
    }

    public int getInt(int paramInt) {
        return 0;
    }

    public long getUnsignedInt(int paramInt) {
        return 0;
    }

    public long getLong(int paramInt) {
        return 0;
    }

    public char getChar(int paramInt) {
        return 0;
    }

    public float getFloat(int paramInt) {
        return 0;
    }

    public double getDouble(int paramInt) {
        return 0;
    }

    public ChannelBuffer getBytes(int paramInt, ChannelBuffer paramByteBuf) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt1, ChannelBuffer paramByteBuf, int paramInt2) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt1, ChannelBuffer paramByteBuf, int paramInt2,
                                  int paramInt3) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt, byte[] paramArrayOfByte) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt, ByteBuffer paramByteBuffer) {
        return null;
    }

    public ChannelBuffer getBytes(int paramInt1, OutputStream paramOutputStream,
                                  int paramInt2) throws IOException {
        return null;
    }

    public int getBytes(int paramInt1, GatheringByteChannel paramGatheringByteChannel,
                        int paramInt2) throws IOException {
        return 0;
    }

    public ChannelBuffer setBoolean(int paramInt, boolean paramBoolean) {
        return null;
    }

    public ChannelBuffer setByte(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer setShort(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer setMedium(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer setInt(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer setLong(int paramInt, long paramLong) {
        return null;
    }

    public ChannelBuffer setChar(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer setFloat(int paramInt, float paramFloat) {
        return null;
    }

    public ChannelBuffer setDouble(int paramInt, double paramDouble) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt, ChannelBuffer paramByteBuf) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt1, ChannelBuffer paramByteBuf, int paramInt2) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt1, ChannelBuffer paramByteBuf, int paramInt2,
                                  int paramInt3) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt, byte[] paramArrayOfByte) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        return null;
    }

    public ChannelBuffer setBytes(int paramInt, ByteBuffer paramByteBuffer) {
        return null;
    }

    public int setBytes(int paramInt1, InputStream paramInputStream,
                        int paramInt2) throws IOException {
        return 0;
    }

    public int setBytes(int paramInt1, ScatteringByteChannel paramScatteringByteChannel,
                        int paramInt2) throws IOException {
        return 0;
    }

    public ChannelBuffer setZero(int paramInt1, int paramInt2) {
        return null;
    }

    public boolean readBoolean() {
        return false;
    }

    public byte readByte() {
        return 0;
    }

    public short readUnsignedByte() {
        return 0;
    }

    public short readShort() {
        return 0;
    }

    public int readUnsignedShort() {
        return 0;
    }

    public int readMedium() {
        return 0;
    }

    public int readUnsignedMedium() {
        return 0;
    }

    public int readInt() {
        return 0;
    }

    public long readUnsignedInt() {
        return 0;
    }

    public long readLong() {
        return 0;
    }

    public char readChar() {
        return 0;
    }

    public float readFloat() {
        return 0;
    }

    public double readDouble() {
        return 0;
    }

    public ChannelBuffer readBytes(int paramInt) {
        return null;
    }

    public ChannelBuffer readSlice(int paramInt) {
        return null;
    }

    public ChannelBuffer readBytes(ChannelBuffer paramByteBuf) {
        return null;
    }

    public ChannelBuffer readBytes(ChannelBuffer paramByteBuf, int paramInt) {
        return null;
    }

    public ChannelBuffer readBytes(ChannelBuffer paramByteBuf, int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer readBytes(byte[] paramArrayOfByte) {
        return null;
    }

    public ChannelBuffer readBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer readBytes(ByteBuffer paramByteBuffer) {
        return null;
    }

    public ChannelBuffer readBytes(OutputStream paramOutputStream,
                                   int paramInt) throws IOException {
        return null;
    }

    public int readBytes(GatheringByteChannel paramGatheringByteChannel,
                         int paramInt) throws IOException {
        return 0;
    }

    public ChannelBuffer skipBytes(int paramInt) {
        return null;
    }

    public ChannelBuffer writeBoolean(boolean paramBoolean) {
        return null;
    }

    public ChannelBuffer writeByte(int paramInt) {
        return null;
    }

    public ChannelBuffer writeShort(int paramInt) {
        return null;
    }

    public ChannelBuffer writeMedium(int paramInt) {
        return null;
    }

    public ChannelBuffer writeInt(int paramInt) {
        return null;
    }

    public ChannelBuffer writeLong(long paramLong) {
        return null;
    }

    public ChannelBuffer writeChar(int paramInt) {
        return null;
    }

    public ChannelBuffer writeFloat(float paramFloat) {
        return null;
    }

    public ChannelBuffer writeDouble(double paramDouble) {
        return null;
    }

    public ChannelBuffer writeBytes(ChannelBuffer paramByteBuf) {
        return null;
    }

    public ChannelBuffer writeBytes(ChannelBuffer paramByteBuf, int paramInt) {
        return null;
    }

    public ChannelBuffer writeBytes(ChannelBuffer paramByteBuf, int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte) {
        return null;
    }

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer writeBytes(ByteBuffer paramByteBuffer) {
        return null;
    }

    public int writeBytes(InputStream paramInputStream, int paramInt) throws IOException {
        return 0;
    }

    public int writeBytes(ScatteringByteChannel paramScatteringByteChannel,
                          int paramInt) throws IOException {
        return 0;
    }

    public ChannelBuffer writeZero(int paramInt) {
        return null;
    }

    public int indexOf(int paramInt1, int paramInt2, byte paramByte) {
        return 0;
    }

    public int bytesBefore(byte paramByte) {
        return 0;
    }

    public int bytesBefore(int paramInt, byte paramByte) {
        return 0;
    }

    public int bytesBefore(int paramInt1, int paramInt2, byte paramByte) {
        return 0;
    }

    public ChannelBuffer copy() {
        return null;
    }

    public ChannelBuffer copy(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer slice() {
        return null;
    }

    public ChannelBuffer slice(int paramInt1, int paramInt2) {
        return null;
    }

    public ChannelBuffer duplicate() {
        return null;
    }

    public int nioBufferCount() {
        return 0;
    }

    public ByteBuffer nioBuffer() {
        return null;
    }

    public ByteBuffer nioBuffer(int paramInt1, int paramInt2) {
        return null;
    }

    public ByteBuffer internalNioBuffer(int paramInt1, int paramInt2) {
        return null;
    }

    public ByteBuffer[] nioBuffers() {
        return null;
    }

    public ByteBuffer[] nioBuffers(int paramInt1, int paramInt2) {
        return null;
    }

    public boolean hasArray() {
        return false;
    }

    public byte[] array() {
        return null;
    }

    public int arrayOffset() {
        return 0;
    }

    public boolean hasMemoryAddress() {
        return false;
    }

    public long memoryAddress() {
        return 0;
    }

    public String toString(Charset paramCharset) {
        return null;
    }

    public String toString(int paramInt1, int paramInt2, Charset paramCharset) {
        return null;
    }

    public int compareTo(ChannelBuffer paramByteBuf) {
        return 0;
    }

    public ChannelBuffer retain(int paramInt) {
        return null;
    }

    public ChannelBuffer retain() {
        return null;
    }

    public ByteOrder order() {
        return null;
    }
}
