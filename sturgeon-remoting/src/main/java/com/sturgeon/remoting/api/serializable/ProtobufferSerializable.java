package com.sturgeon.remoting.api.serializable;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

import io.protostuff.Input;
import io.protostuff.LinkedBuffer;
import io.protostuff.Output;
import io.protostuff.Pipe;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.WireFormat.FieldType;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.Delegate;
import io.protostuff.runtime.RuntimeEnv;
import io.protostuff.runtime.RuntimeSchema;

/**
 * Protobuffer编解码器
 * @author tianxiao
 * @version $Id: ProtobufferCodec.java, v 0.1 2016年12月2日 下午4:18:07 tianxiao Exp $
 */
public class ProtobufferSerializable implements Serializable {

    private final Delegate<Timestamp>                           timestampDelegate;

    private final static DefaultIdStrategy                      idStrategy   = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);

    private final static ConcurrentHashMap<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    public ProtobufferSerializable() {
        timestampDelegate = new TimestampDelegate();
        idStrategy.registerDelegate(timestampDelegate);
    }

    @Override
    public <T> T decode(byte[] bytes, Class<T> clz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            Schema<T> schema = getSchema(clz);
            //改为由Schema来实例化解码对象，没有构造函数也没有问题
            T message = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public <T> byte[] encode(T object) {
        if (object == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> cls = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            byte[] bytes = ProtostuffIOUtil.toByteArray(object, schema, buffer);
            return bytes;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    private <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz, idStrategy);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    class TimestampDelegate implements Delegate<Timestamp> {

        @Override
        public FieldType getFieldType() {
            return FieldType.FIXED64;
        }

        @Override
        public Class<?> typeClass() {
            return Timestamp.class;
        }

        @Override
        public Timestamp readFrom(Input input) throws IOException {
            return new Timestamp(input.readFixed64());
        }

        @Override
        public void writeTo(Output output, int number, Timestamp value,
                            boolean repeated) throws IOException {
            output.writeFixed64(number, value.getTime(), repeated);
        }

        @Override
        public void transfer(Pipe pipe, Input input, Output output, int number,
                             boolean repeated) throws IOException {
            output.writeFixed64(number, input.readFixed64(), repeated);
        }

    }
}
