package com.sturgeon.common;

/**
 * 常量池
 * @author tianxiao
 * @version $Id: Constants.java, v 0.1 2016年12月5日 上午11:58:14 tianxiao Exp $
 */
public class Constants {

    public static final int    MIN_BUFFER_SIZE          = 32;

    public static final String CODEC_KEY                = "codec";

    public static final String IO_THREADS_KEY           = "iothreads";

    public static final int    DEFAULT_IO_THREADS       = Runtime.getRuntime().availableProcessors()
                                                          + 1;

    public static final String DEFAULT_KEY_PREFIX       = "default.";

    public static final String TIME_OUT_KEY             = "timeout";

    public static final String READER_IDLE_TIME_KEY     = "readerIdleTime";

    public static final int    DEFAULT_READER_IDLE_TIME = 3;

    public static final String WRITER_IDLE_TIME_KEY     = "writerIdleTime";

    public static final int    DEFAULT_WRITER_IDLE_TIME = 1;

    public static final String ALL_IDLE_TIME_KEY        = "allIdleTime";

    public static final int    DEFAULT_ALL_IDEL_TIME    = 0;

    public static final int    DEFAULT_TIME_OUT         = 1000;
    
    public static final String DEFAULT_SERIALIZABLE     = "protobuffer";
}
