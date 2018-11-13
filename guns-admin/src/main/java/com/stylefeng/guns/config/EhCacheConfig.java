package com.stylefeng.guns.config;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * ehcache配置
 *
 * @author fengshuonan
 * @date 2017-05-20 23:11
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    /**
     * EhCache的配置
     * 是spring的实现类，spring缓存主要的核心类
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }

    /**
     * EhCache的配置
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    /**
     * EhCache的配置
     * 是spring缓存的主类，spring框架提供的
     */
    /*@Bean
    public Redisca redisCacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }*/

}
