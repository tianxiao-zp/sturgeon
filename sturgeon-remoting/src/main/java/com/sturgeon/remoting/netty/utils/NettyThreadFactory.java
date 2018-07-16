package com.sturgeon.remoting.netty.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * netty线程工厂，对ThreadFactory进行重构方便线程追踪
 * @author tianxiao
 * @version $Id: NettyThreadFactory.java, v 0.1 2016年12月6日 下午3:02:50 tianxiao Exp $
 */
public class NettyThreadFactory implements ThreadFactory {
    private final AtomicInteger        threadNum = new AtomicInteger(1);
    /** 线程名称前缀 @author tianxiao 2016年12月1日 下午12:00:21 */
    private final String               prefix;

    /** 是否是守护线程，true为守护线程  @author tianxiao 2016年12月1日 上午11:59:36 */
    private final boolean              daemo;

    /** 线程组 @author tianxiao 2016年12月1日 上午11:57:38 */
    private final ThreadGroup          group;

    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNum.getAndIncrement();
        Thread ret = new Thread(group, runnable, name, 0);
        ret.setDaemon(daemo);
        return ret;
    }

    public NettyThreadFactory(String prefix) {
        this(prefix, true);
    }

    private NettyThreadFactory(String prefix, boolean daemo) {
        this.prefix = prefix + "-thread-";
        this.daemo = daemo;
        SecurityManager s = System.getSecurityManager();
        this.group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public ThreadGroup getThreadGroup() {
        return group;
    }
}
