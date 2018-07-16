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

    @Override
    public int capacity() {
        return byteBuf.capacity();
    }

    @Override
    public ChannelBuffer capacity(int paramInt) {
        this.byteBuf.capacity(paramInt);
        return this;
    }

    @Override
    public int maxCapacity() {
        return this.byteBuf.maxCapacity();
    }

    @Override
    public ChannelBuffer order(ByteOrder paramByteOrder) {
        this.byteBuf.order(paramByteOrder);
        return this;
    }

    @Override
    public ChannelBuffer unwrap() {
        this.byteBuf.unwrap();
        return this;
    }

    @Override
    public boolean isDirect() {
        return this.byteBuf.isDirect();
    }

    @Override
    public int readerIndex() {
        return this.byteBuf.readerIndex();
    }

    @Override
    public ChannelBuffer readerIndex(int paramInt) {
        this.byteBuf.readerIndex(paramInt);
        return this;
    }

    @Override
    public int writerIndex() {
        return this.byteBuf.writerIndex();
    }

    @Override
    public ChannelBuffer writerIndex(int paramInt) {
        this.byteBuf.writerIndex(paramInt);
        return this;
    }

    @Override
    public ChannelBuffer setIndex(int paramInt1, int paramInt2) {
        this.byteBuf.setIndex(paramInt1, paramInt2);
        return this;
    }

    @Override
    public int readableBytes() {
        return this.byteBuf.readableBytes();
    }

    @Override
    public int writableBytes() {
        return this.byteBuf.writableBytes();
    }

    @Override
    public int maxWritableBytes() {
        return this.byteBuf.maxWritableBytes();
    }

    @Override
    public boolean isReadable() {
        return this.byteBuf.isReadable();
    }

    @Override
    public boolean isReadable(int paramInt) {
        return this.byteBuf.isReadable(paramInt);
    }

    @Override
    public boolean isWritable() {
        return this.byteBuf.isWritable();
    }

    @Override
    public boolean isWritable(int paramInt) {
        return this.byteBuf.isWritable(paramInt);
    }

    @Override
    public ChannelBuffer clear() {
        this.byteBuf.clear();
        return this;
    }

    @Override
    public ChannelBuffer markReaderIndex() {
        this.byteBuf.markReaderIndex();
        return this;
    }

    @Override
    public ChannelBuffer resetReaderIndex() {
        this.byteBuf.resetReaderIndex();
        return this;
    }

    @Override
    public ChannelBuffer markWriterIndex() {
        this.byteBuf.markWriterIndex();
        return this;
    }

    @Override
    public ChannelBuffer resetWriterIndex() {
        this.byteBuf.resetWriterIndex();
        return this;
    }

    @Override
    public ChannelBuffer discardReadBytes() {
        this.byteBuf.discardReadBytes();
        return this;
    }

    @Override
    public ChannelBuffer discardSomeReadBytes() {
        this.byteBuf.discardSomeReadBytes();
        return this;
    }

    @Override
    public ChannelBuffer ensureWritable(int paramInt) {
        this.byteBuf.ensureWritable(paramInt);
        return this;
    }

    @Override
    public int ensureWritable(int paramInt, boolean paramBoolean) {
        return this.byteBuf.ensureWritable(paramInt, paramBoolean);
    }

    @Override
    public boolean getBoolean(int paramInt) {
        return this.byteBuf.getBoolean(paramInt);
    }

    @Override
    public byte getByte(int paramInt) {
        return this.byteBuf.getByte(paramInt);
    }

    @Override
    public short getUnsignedByte(int paramInt) {
        return this.byteBuf.getUnsignedByte(paramInt);
    }

    @Override
    public short getShort(int paramInt) {
        return this.byteBuf.getShort(paramInt);
    }

    @Override
    public int getUnsignedShort(int paramInt) {
        return this.byteBuf.getUnsignedShort(paramInt);
    }

    @Override
    public int getMedium(int paramInt) {
        return this.byteBuf.getMedium(paramInt);
    }

    @Override
    public int getUnsignedMedium(int paramInt) {
        return this.byteBuf.getUnsignedMedium(paramInt);
    }

    @Override
    public int getInt(int paramInt) {
        return this.byteBuf.getInt(paramInt);
    }

    @Override
    public long getUnsignedInt(int paramInt) {
        return this.byteBuf.getUnsignedInt(paramInt);
    }

    @Override
    public long getLong(int paramInt) {
        return this.byteBuf.getLong(paramInt);
    }

    @Override
    public char getChar(int paramInt) {
        return this.byteBuf.getChar(paramInt);
    }

    @Override
    public float getFloat(int paramInt) {
        return this.byteBuf.getFloat(paramInt);
    }

    @Override
    public double getDouble(int paramInt) {
        return this.byteBuf.getDouble(paramInt);
    }

    @Override
    public ChannelBuffer getBytes(int paramInt, byte[] paramArrayOfByte) {
        this.byteBuf.getBytes(paramInt, paramArrayOfByte);
        return this;
    }

    @Override
    public ChannelBuffer getBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        this.byteBuf.getBytes(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
        return this;
    }

    @Override
    public ChannelBuffer getBytes(int paramInt, ByteBuffer paramByteBuffer) {
        this.byteBuf.getBytes(paramInt, paramByteBuffer);
        return this;
    }

    @Override
    public ChannelBuffer getBytes(int paramInt1, OutputStream paramOutputStream,
                                  int paramInt2) throws IOException {
        this.byteBuf.getBytes(paramInt1, paramOutputStream, paramInt2);
        return this;
    }

    @Override
    public int getBytes(int paramInt1, GatheringByteChannel paramGatheringByteChannel,
                        int paramInt2) throws IOException {
        return this.byteBuf.getBytes(paramInt1, paramGatheringByteChannel, paramInt2);
    }

    @Override
    public ChannelBuffer setBoolean(int paramInt, boolean paramBoolean) {
        this.byteBuf.setBoolean(paramInt, paramBoolean);
        return this;
    }

    @Override
    public ChannelBuffer setByte(int paramInt1, int paramInt2) {
        this.byteBuf.setByte(paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer setShort(int paramInt1, int paramInt2) {
        this.byteBuf.setShort(paramInt1, paramInt2);
        return this;
    }

    @Override
    public ChannelBuffer setMedium(int paramInt1, int paramInt2) {
        this.byteBuf.setMedium(paramInt1, paramInt2);
        return this;
    }

    @Override
    public ChannelBuffer setInt(int paramInt1, int paramInt2) {
        this.byteBuf.setInt(paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer setLong(int paramInt, long paramLong) {
        this.byteBuf.setLong(paramInt, paramLong);
        return this;
    }
    @Override
    public ChannelBuffer setChar(int paramInt1, int paramInt2) {
        this.byteBuf.setChar(paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer setFloat(int paramInt, float paramFloat) {
        this.byteBuf.setFloat(paramInt, paramFloat);
        return this;
    }
    @Override
    public ChannelBuffer setDouble(int paramInt, double paramDouble) {
        this.byteBuf.setDouble(paramInt, paramDouble);
        return this;
    }
    @Override
    public ChannelBuffer setBytes(int paramInt, byte[] paramArrayOfByte) {
        this.byteBuf.setBytes(paramInt, paramArrayOfByte);
        return this;
    }
    @Override
    public ChannelBuffer setBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2,
                                  int paramInt3) {
        this.byteBuf.setBytes(paramInt1, paramArrayOfByte, paramInt2, paramInt3);
        return this;
    }
    @Override
    public ChannelBuffer setBytes(int paramInt, ByteBuffer paramByteBuffer) {
        this.byteBuf.setBytes(paramInt, paramByteBuffer);
        return this;
    }
    @Override
    public int setBytes(int paramInt1, InputStream paramInputStream,
                        int paramInt2) throws IOException {
        return this.byteBuf.setBytes(paramInt1, paramInputStream, paramInt2);
    }
    @Override
    public int setBytes(int paramInt1, ScatteringByteChannel paramScatteringByteChannel,
                        int paramInt2) throws IOException {
        return this.byteBuf.setBytes(paramInt1, paramScatteringByteChannel, paramInt2);
    }
    @Override
    public ChannelBuffer setZero(int paramInt1, int paramInt2) {
        this.byteBuf.setZero(paramInt1, paramInt2);
        return this;
    }
    @Override
    public boolean readBoolean() {
        return this.byteBuf.readBoolean();
    }
    @Override
    public byte readByte() {
        return this.byteBuf.readByte();
    }
    @Override
    public short readUnsignedByte() {
        return this.byteBuf.readUnsignedByte();
    }
    @Override
    public short readShort() {
        return this.byteBuf.readShort();
    }
    @Override
    public int readUnsignedShort() {
        return this.byteBuf.readUnsignedShort();
    }
    @Override
    public int readMedium() {
        return this.byteBuf.readMedium();
    }
    @Override
    public int readUnsignedMedium() {
        return this.byteBuf.readUnsignedMedium();
    }
    @Override
    public int readInt() {
        return this.byteBuf.readInt();
    }
    @Override
    public long readUnsignedInt() {
        return this.byteBuf.readUnsignedInt();
    }
    @Override
    public long readLong() {
        return this.byteBuf.readLong();
    }
    @Override
    public char readChar() {
        return this.byteBuf.readChar();
    }
    @Override
    public float readFloat() {
        return this.byteBuf.readFloat();
    }
    @Override
    public double readDouble() {
        return this.byteBuf.readDouble();
    }
    @Override
    public ChannelBuffer readBytes(int paramInt) {
        this.byteBuf.readBytes(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer readSlice(int paramInt) {
        this.byteBuf.readSlice(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer readBytes(byte[] paramArrayOfByte) {
        this.byteBuf.readBytes(paramArrayOfByte);
        return this;
    }
    @Override
    public ChannelBuffer readBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        this.byteBuf.readBytes(paramArrayOfByte, paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer readBytes(ByteBuffer paramByteBuffer) {
        this.byteBuf.readBytes(paramByteBuffer);
        return this;
    }
    @Override
    public ChannelBuffer readBytes(OutputStream paramOutputStream,
                                   int paramInt) throws IOException {
        this.byteBuf.readBytes(paramOutputStream, paramInt);
        return this;
    }
    @Override
    public int readBytes(GatheringByteChannel paramGatheringByteChannel,
                         int paramInt) throws IOException {
        return this.byteBuf.readBytes(paramGatheringByteChannel, paramInt);
    }
    @Override
    public ChannelBuffer skipBytes(int paramInt) {
        this.byteBuf.skipBytes(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeBoolean(boolean paramBoolean) {
        this.byteBuf.writeBoolean(paramBoolean);
        return this;
    }
    @Override
    public ChannelBuffer writeByte(int paramInt) {
        this.byteBuf.writeByte(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeShort(int paramInt) {
        this.byteBuf.writeShort(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeMedium(int paramInt) {
        this.byteBuf.writeMedium(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeInt(int paramInt) {
        this.byteBuf.writeInt(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeLong(long paramLong) {
        this.byteBuf.writeLong(paramLong);
        return this;
    }
    @Override
    public ChannelBuffer writeChar(int paramInt) {
        this.byteBuf.writeChar(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer writeFloat(float paramFloat) {
        this.byteBuf.writeFloat(paramFloat);
        return this;
    }
    @Override
    public ChannelBuffer writeDouble(double paramDouble) {
        this.byteBuf.writeDouble(paramDouble);
        return this;
    }
    @Override
    public ChannelBuffer writeBytes(byte[] paramArrayOfByte) {
        this.byteBuf.writeBytes(paramArrayOfByte);
        return this;
    }
    @Override
    public ChannelBuffer writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        this.byteBuf.writeBytes(paramArrayOfByte, paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer writeBytes(ByteBuffer paramByteBuffer) {
        this.byteBuf.writeBytes(paramByteBuffer);
        return this;
    }
    @Override
    public int writeBytes(InputStream paramInputStream, int paramInt) throws IOException {
        return this.byteBuf.writeBytes(paramInputStream, paramInt);
    }
    @Override
    public int writeBytes(ScatteringByteChannel paramScatteringByteChannel,
                          int paramInt) throws IOException {
        return this.byteBuf.writeBytes(paramScatteringByteChannel, paramInt);
    }
    @Override
    public ChannelBuffer writeZero(int paramInt) {
        this.byteBuf.writeZero(paramInt);
        return this;
    }
    @Override
    public int indexOf(int paramInt1, int paramInt2, byte paramByte) {
        return this.byteBuf.indexOf(paramInt1, paramInt2, paramByte);
    }
    @Override
    public int bytesBefore(byte paramByte) {
        return this.byteBuf.bytesBefore(paramByte);
    }
    @Override
    public int bytesBefore(int paramInt, byte paramByte) {
        return this.byteBuf.bytesBefore(paramInt, paramByte);
    }
    @Override
    public int bytesBefore(int paramInt1, int paramInt2, byte paramByte) {
        return this.byteBuf.bytesBefore(paramInt1, paramInt2, paramByte);
    }
    @Override
    public ChannelBuffer copy() {
        this.byteBuf.copy();
        return this;
    }
    @Override
    public ChannelBuffer copy(int paramInt1, int paramInt2) {
        this.byteBuf.copy(paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer slice() {
        this.byteBuf.slice();
        return this;
    }
    @Override
    public ChannelBuffer slice(int paramInt1, int paramInt2) {
        this.byteBuf.slice(paramInt1, paramInt2);
        return this;
    }
    @Override
    public ChannelBuffer duplicate() {
        this.byteBuf.duplicate();
        return this;
    }
    @Override
    public int nioBufferCount() {
        return this.byteBuf.nioBufferCount();
    }
    @Override
    public ByteBuffer nioBuffer() {
        return this.byteBuf.nioBuffer();
    }
    @Override
    public ByteBuffer nioBuffer(int paramInt1, int paramInt2) {
        return this.byteBuf.nioBuffer(paramInt1, paramInt2);
    }
    @Override
    public ByteBuffer internalNioBuffer(int paramInt1, int paramInt2) {
        return this.byteBuf.internalNioBuffer(paramInt1, paramInt2);
    }
    @Override
    public ByteBuffer[] nioBuffers() {
        return this.byteBuf.nioBuffers();
    }
    @Override
    public ByteBuffer[] nioBuffers(int paramInt1, int paramInt2) {
        return this.byteBuf.nioBuffers(paramInt1, paramInt2);
    }
    @Override
    public boolean hasArray() {
        return this.byteBuf.hasArray();
    }
    @Override
    public byte[] array() {
        return this.byteBuf.array();
    }
    @Override
    public int arrayOffset() {
        return this.byteBuf.arrayOffset();
    }
    @Override
    public boolean hasMemoryAddress() {
        return this.byteBuf.hasMemoryAddress();
    }
    @Override
    public long memoryAddress() {
        return this.byteBuf.memoryAddress();
    }
    @Override
    public String toString(Charset paramCharset) {
        return this.byteBuf.toString(paramCharset);
    }
    @Override
    public String toString(int paramInt1, int paramInt2, Charset paramCharset) {
        return this.byteBuf.toString(paramInt1, paramInt2, paramCharset);
    }
    @Override
    public int compareTo(ChannelBuffer paramByteBuf) {
        return this.byteBuf.compareTo(byteBuf);
    }
    @Override
    public ChannelBuffer retain(int paramInt) {
        this.byteBuf.retain(paramInt);
        return this;
    }
    @Override
    public ChannelBuffer retain() {
        this.byteBuf.retain();
        return this;
    }
    @Override
    public ByteOrder order() {
        return this.byteBuf.order();
    }

    public io.netty.buffer.ByteBuf getByteBuf() {
        return byteBuf;
    }
}
