package com.sturgeon.common;

/**
 * 常量池
 * @author tianxiao
 * @version $Id: Constants.java, v 0.1 2016年12月5日 上午11:58:14 tianxiao Exp $
 */
public class Constants {

    public static final int    MIN_BUFFER_SIZE    = 32;

    public static final String CODEC_KEY          = "codec";

    public static final String IO_THREADS_KEY     = "iothreads";

    public static final int    DEFAULT_IO_THREADS = Runtime.getRuntime().availableProcessors() + 1;

    public static final String DEFAULT_KEY_PREFIX = "default.";

    public static final String TIME_OUT_KEY       = "timeout";

    public static final int    DEFAULT_TIME_OUT   = 1000;
}
