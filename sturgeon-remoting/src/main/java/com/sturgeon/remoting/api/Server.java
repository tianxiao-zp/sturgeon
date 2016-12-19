package com.sturgeon.remoting.api;

import java.util.Collection;

/**
 * 服务端接口
 * @author tianxiao
 * @version $Id: Server.java, v 0.1 2016年12月9日 上午10:25:18 tianxiao Exp $
 */
public interface Server extends Endpoint {
    
    /**
     * 是否已经绑定
     * @author tianxiao
     * 2016年12月9日 上午11:19:25
     * @return
     */
    boolean isBound();
    
    public Collection<Channel> getChannels();
}
