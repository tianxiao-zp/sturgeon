package com.sturgeon.common.utils;

import java.net.InetSocketAddress;

public class NetUtils {
    private NetUtils() {
        // 工具类，避免实力化
    }
    
    /**
     * 获取IP:prot
     * @author tianxiao
     * 2016年12月18日 下午2:24:14
     * @param address
     * @return
     */
    public static String toAddressString(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }
}
