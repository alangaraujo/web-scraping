package br.com.alangaraujo.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@EnableCaching
public class CachingConfig {
	
	CacheManager manager;

    @Bean
    public CacheManager cacheManager() {
    	
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        	
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("documents")));
        
        manager = cacheManager;
        	
        return cacheManager;
    }
    
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void evictCacheAtIntervals() {

    	manager.getCache("documents").clear();
        
    }
    
}
