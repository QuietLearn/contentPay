package com.stylefeng.guns.core.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.stylefeng.guns.core.common.constant.state.AllConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    //前缀是一个区分，也可以把它抽象理解成namespace
    public static final String TOKEN_PREFIX = "token_";

    //LoadingCache<String,String>是guava的缓存形式，以后还有redis
    // 表示缓存是 hashmap形式的内存，且存储kv都是string
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(AllConst.timeout, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值的时候，如果key没有对应值，就调用这个方法进行加载
                @Override
                public String load(String key) throws Exception {
                    return "null"; //避免.equals空指针异常
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value =null; //初始化
        try{
            value = localCache.get(key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        } catch (Exception e){
            logger.error("localCache get error",e);
        }
        return null;
    }


}
