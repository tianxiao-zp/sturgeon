package com.sturgeon.remoting.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
/**
 * 减少对io.netty.buffer.ByteBuffer依赖，对byteBuffer进行抽象
 * @author tianxiao
 * @version $Id: ChannelBuffer.java, v 0.1 2016年12月5日 上午10:25:24 tianxiao Exp $
 */
public interface ChannelBuffer {

    public int capacity();

    public ChannelBuffer capacity(int paramInt);

    public int maxCapacity();

    public ByteOrder order();

    public ChannelBuffer order(ByteOrder paramByteOrder);

    public ChannelBuffer unwrap();

    public boolean isDirect();

    public int readerIndex();

    public ChannelBuffer readerIndex(int paramInt);

    public int writerIndex();

    public ChannelBuffer writerIndex(int paramInt);

    public ChannelBuffer setIndex(int paramInt1, int paramInt2);

    public int readableBytes();

    public int writableBytes();

    public int maxWritableBytes();

    public boolean isReadable();

    public boolean isReadable(int paramInt);

    public boolean isWritable();

    public boolean isWritable(int paramInt);

    public ChannelBuffer clear();

    public ChannelBuffer markReaderIndex();

    public ChannelBuffer resetReaderIndex();

    public ChannelBuffer markWriterIndex();

    public ChannelBuffer resetWriterIndex();

    public ChannelBuffer discardReadBytes();

    public ChannelBuffer discardSomeReadBytes();

    public ChannelBuffer ensureWritable(int paramInt);

    public int ensureWritable(int paramInt, boolean paramBoolean);

    public boolean getBoolean(int paramInt);

    public byte getByte(int paramInt);

    public short getUnsignedByte(int paramInt);

    public short getShort(int paramInt);

    public int getUnsignedShort(int paramInt);

    public int getMedium(int paramInt);

    public int getUnsignedMedium(int paramInt);

    public int getInt(int paramInt);

    public long getUnsignedInt(int paramInt);

    public long getLong(int paramInt);

    public char getChar(int paramInt);

    public float getFloat(int paramInt);

    public double getDouble(int paramInt);

    public ChannelBuffer getBytes(int paramInt, byte[] paramArrayOfByte);

    public ChannelBuffer getBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);

    public ChannelBuffer getBytes(int paramInt, ByteBuffer paramByteBuffer);

    public ChannelBuffer getBytes(int paramInt1, OutputStream paramOutputStream,
                            int paramInt2) throws IOException;

    public int getBytes(int paramInt1, GatheringByteChannel paramGatheringByteChannel,
                        int paramInt2) throws IOException;

    public ChannelBuffer setBoolean(int paramInt, boolean paramBoolean);

    public ChannelBuffer setByte(int paramInt1, int paramInt2);

    public ChannelBuffer setShort(int paramInt1, int paramInt2);

    public ChannelBuffer setMedium(int paramInt1, int paramInt2);

    public ChannelBuffer setInt(int paramInt1, int paramInt2);

    public ChannelBuffer setLong(int paramInt, long paramLong);

    public ChannelBuffer setChar(int paramInt1, int paramInt2);

    public ChannelBuffer setFloat(int paramInt, float paramFloat);

    public ChannelBuffer setDouble(int paramInt, double paramDouble);

    public ChannelBuffer setBytes(int paramInt, byte[] paramArrayOfByte);

    public ChannelBuffer setBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);

    public ChannelBuffer setBytes(int paramInt, ByteBuffer paramByteBuffer);

    public int setBytes(int paramInt1, InputStream paramInputStream,
                        int paramInt2) throws IOException;

    public int setBytes(int paramInt1, ScatteringByteChannel paramScatteringByteChannel,
                        int paramInt2) throws IOException;

    public ChannelBuffer setZero(int paramInt1, int paramInt2);

    public boolean readBoolean();

    public byte readByte();

    public short readUnsignedByte();

    public short readShort();

    public int readUnsignedShort();

    public int readMedium();

    public int readUnsignedMedium();

    public int readInt();

    public long readUnsignedInt();

    public long readLong();

    public char readChar();

    public float readFloat();

    public double readDouble();

    public ChannelBuffer readBytes(int paramInt);

    public ChannelBuffer readSlice(int paramInt);

    public ChannelBuffer readBytes(byte[] paramArrayOfByte);

    public ChannelBuffer readBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    public ChannelBuffer readBytes(ByteBuffer paramByteBuffer);

    public ChannelBuffer readBytes(OutputStream paramOutputStream, int paramInt) throws IOException;

    public int readBytes(GatheringByteChannel paramGatheringByteChannel,
                         int paramInt) throws IOException;

    public ChannelBuffer skipBytes(int paramInt);

    public ChannelBuffer writeBoolean(boolean paramBoolean);

    public ChannelBuffer writeByte(int paramInt);

    public ChannelBuffer writeShort(int paramInt);

    public ChannelBuffer writeMedium(int paramInt);

    public ChannelBuffer writeInt(int paramInt);

    public ChannelBuffer writeLong(long paramLong);

    public ChannelBuffer writeChar(int paramInt);

    public ChannelBuffer writeFloat(float paramFloat);

    public ChannelBuffer writeDouble(double paramDouble);

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte);

    public ChannelBuffer writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

    public ChannelBuffer writeBytes(ByteBuffer paramByteBuffer);

    public int writeBytes(InputStream paramInputStream, int paramInt) throws IOException;

    public int writeBytes(ScatteringByteChannel paramScatteringByteChannel,
                          int paramInt) throws IOException;

    public ChannelBuffer writeZero(int paramInt);

    public int indexOf(int paramInt1, int paramInt2, byte paramByte);

    public int bytesBefore(byte paramByte);

    public int bytesBefore(int paramInt, byte paramByte);

    public int bytesBefore(int paramInt1, int paramInt2, byte paramByte);

    public ChannelBuffer copy();

    public ChannelBuffer copy(int paramInt1, int paramInt2);

    public ChannelBuffer slice();

    public ChannelBuffer slice(int paramInt1, int paramInt2);

    public ChannelBuffer duplicate();

    public int nioBufferCount();

    public ByteBuffer nioBuffer();

    public ByteBuffer nioBuffer(int paramInt1, int paramInt2);

    public ByteBuffer internalNioBuffer(int paramInt1, int paramInt2);

    public ByteBuffer[] nioBuffers();

    public ByteBuffer[] nioBuffers(int paramInt1, int paramInt2);

    public boolean hasArray();

    public byte[] array();

    public int arrayOffset();

    public boolean hasMemoryAddress();

    public long memoryAddress();

    public String toString(Charset paramCharset);

    public String toString(int paramInt1, int paramInt2, Charset paramCharset);

    public int hashCode();

    public boolean equals(Object paramObject);

    public int compareTo(ChannelBuffer paramByteBuf);

    public String toString();

    public ChannelBuffer retain(int paramInt);

    public ChannelBuffer retain();
}
