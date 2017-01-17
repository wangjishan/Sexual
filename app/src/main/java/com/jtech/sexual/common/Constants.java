package com.jtech.sexual.common;

/**
 * 通用参数
 * Created by JTech on 2017/1/16.
 */

public class Constants {
    /**
     * 缓存名
     */
    public static final String KEY_CACHE_NAME = "CACHE_NAME";
    /**
     * https
     */
    public static final String HTTPS = "https://";
    /**
     * 地址
     */
    public static final String BASE_URL = HTTPS + "route.showapi.com";
    /**
     * app秘钥
     */
    public static final String APP_SECRET = "a0205e9504bf4cf6b279cb22835ae539";
    /**
     * 应用id
     */
    public static final String APP_ID = "30916";
    /**
     * 签名生成方式（"md5"或"hmac"）
     */
    public static final String APP_SIGN_METHOD = "md5";
    /**
     * 返回值是否用gzip方式压缩（1或0）
     */
    public static final String APP_RES_GZIP = "0";
}