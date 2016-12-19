package com.sturgeon.remoting.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

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
        return this.byteBuf.readerIndex();
    }

    public ChannelBuffer readerIndex(int paramInt) {
        this.byteBuf.readerIndex(paramInt);
        return this;
    }

    public int writerIndex() {
        return this.byteBuf.writerIndex();
    }

    public ChannelBuffer writerIndex(int paramInt) {
        this.byteBuf.writerIndex(paramInt);
        return this;
    }

    public ChannelBuffer setIndex(int paramInt1, int paramInt2) {
        this.byteBuf.setIndex(paramInt1, paramInt2);
        return this;
    }

    public int readableBytes() {
        return this.byteBuf.readableBytes();
    }

    public int writableBytes() {
        return this.byteBuf.writableBytes();
    }

    public int maxWritableBytes() {
        return this.byteBuf.maxWritableBytes();
    }

    public boolean isReadable() {
        return this.byteBuf.isReadable();
    }

    public boolean isReadable(int paramInt) {
        return this.byteBuf.isReadable(paramInt);
    }

    public boolean isWritable() {
        return this.byteBuf.isWritable();
    }

    public boolean isWritable(int paramInt) {
        return this.byteBuf.isWritable(paramInt);
    }

    public ChannelBuffer clear() {
        this.byteBuf.clear();
        return this;
    }

    public ChannelBuffer markReaderIndex() {
        this.byteBuf.markReaderIndex();
        return this;
    }

    public ChannelBuffer resetReaderIndex() {
        this.byteBuf.resetReaderIndex();
        return this;
    }

    public ChannelBuffer markWriterIndex() {
        this.byteBuf.markWriterIndex();
        return this;
    }

    public ChannelBuffer resetWriterIndex() {
        this.byteBuf.resetWriterIndex();
        return this;
    }

    public ChannelBuffer discardReadBytes() {
        this.byteBuf.discardReadBytes();
        return this;
    }

    public ChannelBuffer discardSomeReadBytes() {
        this.byteBuf.discardSomeReadBytes();
        return this;
    }

    public ChannelBuffer ensureWritable(int paramInt) {
        this.byteBuf.ensureWritable(paramInt);
        return this;
    }

    public int ensureWritable(int paramInt, boolean paramBoolean) {
        return this.byteBuf.ensureWritable(paramInt, paramBoolean);
    }

    public boolean getBoolean(int paramInt) {
        return this.byteBuf.getBoolean(paramInt);
    }

    public byte getByte(int paramInt) {
        return this.byteBuf.getByte(paramInt);
    }

    public short getUnsignedByte(int paramInt) {
        return this.byteBuf.getUnsignedByte(paramInt);
    }

    public short getShort(int paramInt) {
        return this.byteBuf.getShort(paramInt);
    }

    public int getUnsignedShort(int paramInt) {
        return this.byteBuf.getUnsignedShort(paramInt);
    }

    public int getMedium(int paramInt) {
        return this.byteBuf.getMedium(paramInt);
    }

    public int getUnsignedMedium(int paramInt) {
        return this.byteBuf.getUnsignedMedium(paramInt);
    }

    public int getInt(int paramInt) {
        return this.byteBuf.getInt(paramInt);
    }

    public long getUnsignedInt(int paramInt) {
        return this.byteBuf.getUnsignedInt(paramInt);
    }

    public long getLong(int paramInt) {
        return this.byteBuf.getLong(paramInt);
    }

    public char getChar(int paramInt) {
        return this.byteBuf.getChar(paramInt);
    }

    public float getFloat(int paramInt) {
        return this.byteBuf.getFloat(paramInt);
    }

    public double getDouble(int paramInt) {
        return this.byteBuf.getDouble(paramInt);
    }

    public ChannelBuffer getBytes(int paramInt, byte[] paramArrayOfByte) {
        this.byteBuf.getBytes(paramInt, paramArrayOfByte);
        return this;
    }

    public ChannelBuffer getBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        this.byteBuf.getBytes(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
        return this;
    }

    public ChannelBuffer getBytes(int paramInt, ByteBuffer paramByteBuffer) {
        this.byteBuf.getBytes(paramInt, paramByteBuffer);
        return this;
    }

    public ChannelBuffer getBytes(int paramInt1, OutputStream paramOutputStream,
                                  int paramInt2) throws IOException {
        this.byteBuf.getBytes(paramInt1, paramOutputStream, paramInt2);
        return this;
    }

    public int getBytes(int paramInt1, GatheringByteChannel paramGatheringByteChannel,
                        int paramInt2) throws IOException {
        return this.byteBuf.getBytes(paramInt1, paramGatheringByteChannel, paramInt2);
    }

    public ChannelBuffer setBoolean(int paramInt, boolean paramBoolean) {
        this.byteBuf.setBoolean(paramInt, paramBoolean);
        return this;
    }

    public ChannelBuffer setByte(int paramInt1, int paramInt2) {
        this.byteBuf.setByte(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer setShort(int paramInt1, int paramInt2) {
        this.byteBuf.setShort(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer setMedium(int paramInt1, int paramInt2) {
        this.byteBuf.setMedium(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer setInt(int paramInt1, int paramInt2) {
        this.byteBuf.setInt(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer setLong(int paramInt, long paramLong) {
        this.byteBuf.setLong(paramInt, paramLong);
        return this;
    }

    public ChannelBuffer setChar(int paramInt1, int paramInt2) {
        this.byteBuf.setChar(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer setFloat(int paramInt, float paramFloat) {
        this.byteBuf.setFloat(paramInt, paramFloat);
        return this;
    }

    public ChannelBuffer setDouble(int paramInt, double paramDouble) {
        this.byteBuf.setDouble(paramInt, paramDouble);
        return this;
    }

    public ChannelBuffer setBytes(int paramInt, byte[] paramArrayOfByte) {
        this.byteBuf.setBytes(paramInt, paramArrayOfByte);
        return this;
    }

    public ChannelBuffer setBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        this.byteBuf.setBytes(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
        return this;
    }

    public ChannelBuffer setBytes(int paramInt, ByteBuffer paramByteBuffer) {
        this.byteBuf.setBytes(paramInt, paramByteBuffer);
        return this;
    }

    public int setBytes(int paramInt1, InputStream paramInputStream,
                        int paramInt2) throws IOException {
        return this.byteBuf.setBytes(paramInt1, paramInputStream, paramInt2);
    }

    public int setBytes(int paramInt1, ScatteringByteChannel paramScatteringByteChannel,
                        int paramInt2) throws IOException {
        return this.byteBuf.setBytes(paramInt1, paramScatteringByteChannel, paramInt2);
    }

    public ChannelBuffer setZero(int paramInt1, int paramInt2) {
        this.byteBuf.setZero(paramInt1, paramInt2);
        return this;
    }

    public boolean readBoolean() {
        return this.byteBuf.readBoolean();
    }

    public byte readByte() {
        return this.byteBuf.readByte();
    }

    public short readUnsignedByte() {
        return this.byteBuf.readUnsignedByte();
    }

    public short readShort() {
        return this.byteBuf.readShort();
    }

    public int readUnsignedShort() {
        return this.byteBuf.readUnsignedShort();
    }

    public int readMedium() {
        return this.byteBuf.readMedium();
    }

    public int readUnsignedMedium() {
        return this.byteBuf.readUnsignedMedium();
    }

    public int readInt() {
        return this.byteBuf.readInt();
    }

    public long readUnsignedInt() {
        return this.byteBuf.readUnsignedInt();
    }

    public long readLong() {
        return this.byteBuf.readLong();
    }

    public char readChar() {
        return this.byteBuf.readChar();
    }

    public float readFloat() {
        return this.byteBuf.readFloat();
    }

    public double readDouble() {
        return this.byteBuf.readDouble();
    }

    public ChannelBuffer readBytes(int paramInt) {
        this.byteBuf.readBytes(paramInt);
        return this;
    }

    public ChannelBuffer readSlice(int paramInt) {
        this.byteBuf.readSlice(paramInt);
        return this;
    }

    public ChannelBuffer readBytes(byte[] paramArrayOfByte) {
        this.byteBuf.readBytes(paramArrayOfByte);
        return this;
    }

    public ChannelBuffer readBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        this.byteBuf.readBytes(paramArrayOfByte, paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer readBytes(ByteBuffer paramByteBuffer) {
        this.byteBuf.readBytes(paramByteBuffer);
        return this;
    }

    public ChannelBuffer readBytes(OutputStream paramOutputStream,
                                   int paramInt) throws IOException {
        this.byteBuf.readBytes(paramOutputStream, paramInt);
        return this;
    }

    public int readBytes(GatheringByteChannel paramGatheringByteChannel,
                         int paramInt) throws IOException {
        return this.byteBuf.readBytes(paramGatheringByteChannel, paramInt);
    }

    public ChannelBuffer skipBytes(int paramInt) {
        this.byteBuf.skipBytes(paramInt);
        return this;
    }

    public ChannelBuffer writeBoolean(boolean paramBoolean) {
        this.byteBuf.writeBoolean(paramBoolean);
        return this;
    }

    public ChannelBuffer writeByte(int paramInt) {
        this.byteBuf.writeByte(paramInt);
        return this;
    }

    public ChannelBuffer writeShort(int paramInt) {
        this.byteBuf.writeShort(paramInt);
        return this;
    }

    public ChannelBuffer writeMedium(int paramInt) {
        this.byteBuf.writeMedium(paramInt);
        return this;
    }

    public ChannelBuffer writeInt(int paramInt) {
        this.byteBuf.writeInt(paramInt);
        return this;
    }

    public ChannelBuffer writeLong(long paramLong) {
        this.byteBuf.writeLong(paramLong);
        return this;
    }

    public ChannelBuffer writeChar(int paramInt) {
        this.byteBuf.writeChar(paramInt);
        return this;
    }

    public ChannelBuffer writeFloat(float paramFloat) {
        this.byteBuf.writeFloat(paramFloat);
        return this;
    }

    public ChannelBuffer writeDouble(double paramDouble) {
        this.byteBuf.writeDouble(paramDouble);
        return this;
    }

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte) {
        this.byteBuf.writeBytes(paramArrayOfByte);
        return this;
    }

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        this.byteBuf.writeBytes(paramArrayOfByte, paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer writeBytes(ByteBuffer paramByteBuffer) {
        this.byteBuf.writeBytes(paramByteBuffer);
        return this;
    }

    public int writeBytes(InputStream paramInputStream, int paramInt) throws IOException {
        return this.byteBuf.writeBytes(paramInputStream, paramInt);
    }

    public int writeBytes(ScatteringByteChannel paramScatteringByteChannel,
                          int paramInt) throws IOException {
        return this.byteBuf.writeBytes(paramScatteringByteChannel, paramInt);
    }

    public ChannelBuffer writeZero(int paramInt) {
        this.byteBuf.writeZero(paramInt);
        return this;
    }

    public int indexOf(int paramInt1, int paramInt2, byte paramByte) {
        return this.byteBuf.indexOf(paramInt1, paramInt2, paramByte);
    }

    public int bytesBefore(byte paramByte) {
        return this.byteBuf.bytesBefore(paramByte);
    }

    public int bytesBefore(int paramInt, byte paramByte) {
        return this.byteBuf.bytesBefore(paramInt, paramByte);
    }

    public int bytesBefore(int paramInt1, int paramInt2, byte paramByte) {
        return this.byteBuf.bytesBefore(paramInt1, paramInt2, paramByte);
    }

    public ChannelBuffer copy() {
        this.byteBuf.copy();
        return this;
    }

    public ChannelBuffer copy(int paramInt1, int paramInt2) {
        this.byteBuf.copy(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer slice() {
        this.byteBuf.slice();
        return this;
    }

    public ChannelBuffer slice(int paramInt1, int paramInt2) {
        this.byteBuf.slice(paramInt1, paramInt2);
        return this;
    }

    public ChannelBuffer duplicate() {
        this.byteBuf.duplicate();
        return this;
    }

    public int nioBufferCount() {
        return this.byteBuf.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        return this.byteBuf.nioBuffer();
    }

    public ByteBuffer nioBuffer(int paramInt1, int paramInt2) {
        return this.byteBuf.nioBuffer(paramInt1, paramInt2);
    }

    public ByteBuffer internalNioBuffer(int paramInt1, int paramInt2) {
        return this.byteBuf.internalNioBuffer(paramInt1, paramInt2);
    }

    public ByteBuffer[] nioBuffers() {
        return this.byteBuf.nioBuffers();
    }

    public ByteBuffer[] nioBuffers(int paramInt1, int paramInt2) {
        return this.byteBuf.nioBuffers(paramInt1, paramInt2);
    }

    public boolean hasArray() {
        return this.byteBuf.hasArray();
    }

    public byte[] array() {
        return this.byteBuf.array();
    }

    public int arrayOffset() {
        return this.byteBuf.arrayOffset();
    }

    public boolean hasMemoryAddress() {
        return this.byteBuf.hasMemoryAddress();
    }

    public long memoryAddress() {
        return this.byteBuf.memoryAddress();
    }

    public String toString(Charset paramCharset) {
        return this.byteBuf.toString(paramCharset);
    }

    public String toString(int paramInt1, int paramInt2, Charset paramCharset) {
        return this.byteBuf.toString(paramInt1, paramInt2, paramCharset);
    }

    public int compareTo(ChannelBuffer paramByteBuf) {
        return this.byteBuf.compareTo(byteBuf);
    }

    public ChannelBuffer retain(int paramInt) {
        this.byteBuf.retain(paramInt);
        return this;
    }

    public ChannelBuffer retain() {
        this.byteBuf.retain();
        return this;
    }

    public ByteOrder order() {
        return this.byteBuf.order();
    }

    public io.netty.buffer.ByteBuf getByteBuf() {
        return byteBuf;
    }
}
