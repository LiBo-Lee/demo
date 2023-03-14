package com.example.demo.common.base;

/**
 * @author LiBo
 * @date 2023-03-08 18:12
 **/
public interface BaseConstant {
    /** 缓存前缀 */
    String cachePrefix = "example-demo:login:";

    /** token缓存前缀 */
    String tokenCachePrefix = cachePrefix+"token:";

    /** request请求头属性 */
    String tokenHeader = "example-demo";

    /** 密码错误次数 */
    String ERROR_COUNT = "example-demo:errorCount:";
}
