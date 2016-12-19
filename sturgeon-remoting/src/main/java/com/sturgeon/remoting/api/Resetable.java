package com.sturgeon.remoting.api;

import com.sturgeon.remoting.api.transport.RemotingConfig;

/**
 * 重置接口
 * @author tianxiao
 * @version $Id: Resetable.java, v 0.1 2016年12月9日 上午10:29:56 tianxiao Exp $
 */
public interface Resetable {
    /**
     * 重置
     * @author tianxiao
     * 2016年12月9日 上午10:24:48
     */
    void reset(RemotingConfig config);
}
