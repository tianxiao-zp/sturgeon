package com.sturgeon.remoting.api;

import com.sturgeon.common.Constants;
import com.sturgeon.remoting.api.codec.Codec;
import com.sturgeon.remoting.api.codec.CodecHolder;
import com.sturgeon.remoting.api.listener.ChannelEventListener;
import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * AbstractEndpoint
 * @author tianxiao
 * @version $Id: AbstractEndpoint.java, v 0.1 2016年12月9日 上午10:53:14 tianxiao Exp $
 */
public abstract class AbstractEndpoint implements Endpoint, Resetable {
    private final RemotingConfig       config;

    private Codec                      codec;

    private volatile boolean           closed;

    private final ChannelEventListener listener;

    public AbstractEndpoint(RemotingConfig config, ChannelEventListener listener) {
        this.config = config;
        this.listener = listener;
        this.codec = getChannelCodec(config);
    }

    public RemotingConfig getConfig() {
        return this.config;
    }

    /**
     * 通过配置获取编码解析器
     * @author tianxiao
     * 2016年12月12日 上午9:54:37
     * @param config
     * @return
     */
    private Codec getChannelCodec(RemotingConfig config) {
        String parameter = config.getParameter(Constants.CODEC_KEY, "sturgeon");
        return CodecHolder.getCodec(parameter);
    }

    public void reset(RemotingConfig config) {
        if (isClosed()) {
            throw new IllegalStateException("Failed to reset config" + config
                                            + ", the endpoint is closed:" + getLocalAddress());
        }

        Codec codec = getChannelCodec(config);
        if (codec != null) {
            this.codec = codec;
        }
    }

    public ChannelEventListener getListener() {
        return listener;
    }

    protected Codec getCodec() {
        return codec;
    }

    protected void setCodec(Codec codec) {
        this.codec = codec;
    }

    public void close() {
        closed = true;
    }

    public void close(int timeout) {
        // TODO 定时
        close();
    }

    public boolean isClosed() {
        return closed;
    }
}
