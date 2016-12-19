package com.sturgeon.common.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂，控制/监控开启线程数量和设置线程名称
 * @author tianxiao
 * @version $Id: NamedThreadFactory.java, v 0.1 2016年12月1日 上午11:56:48 tianxiao Exp $
 */
public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_SEQ  = new AtomicInteger(1);

    private final AtomicInteger        threadNum = new AtomicInteger(1);

    /** 线程名称前缀 @author tianxiao 2016年12月1日 下午12:00:21 */
    private final String               prefix;

    /** 是否是守护线程，true为守护线程  @author tianxiao 2016年12月1日 上午11:59:36 */
    private final boolean              daemo;

    /** 线程组 @author tianxiao 2016年12月1日 上午11:57:38 */
    private final ThreadGroup          group;

    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemo) {
        this.prefix = prefix + "-thread-";
        this.daemo = daemo;
        SecurityManager s = System.getSecurityManager();
        this.group = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public ThreadGroup getThreadGroup() {
        return group;
    }

    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNum.getAndIncrement();
        Thread ret = new Thread(group, runnable, name, 0);
        ret.setDaemon(daemo);
        return ret;
    }
}
