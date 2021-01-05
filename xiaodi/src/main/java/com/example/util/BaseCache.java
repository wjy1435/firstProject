package com.example.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BaseCache {

    private Cache<String, Object> tenMinuteCache = CacheBuilder.newBuilder()
            //缓存初始大小
            .initialCapacity(10)
//            最大值
            .maximumSize(100)
            //并发线程
            .concurrencyLevel(5)   //并发
            //  缓存过期时间   10 分钟
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();


    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
}
