package com.price.comparision.ecom.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.price.comparision.ecom.constants.CacheConstants;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CacheConfig  extends CachingConfigurerSupport {

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration localSearchCache = new CacheConfiguration();
		localSearchCache.setName("localSearchCache");
		localSearchCache.setMemoryStoreEvictionPolicy(CacheConstants.localCacheType);
		localSearchCache.setMaxEntriesLocalHeap(CacheConstants.localCacheHeapSize);
		localSearchCache.setTimeToLiveSeconds(CacheConstants.localCacheLiveTime);

		CacheConfiguration globalSearchCache = new CacheConfiguration();
		globalSearchCache.setName("globalSearchCache");
		globalSearchCache.setMemoryStoreEvictionPolicy(CacheConstants.globalCacheType);
		globalSearchCache.setMaxEntriesLocalHeap(CacheConstants.globalCacheHeapSize);
		globalSearchCache.setTimeToLiveSeconds(CacheConstants.globalCacheLiveTime);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(localSearchCache);
		config.addCache(globalSearchCache);
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}
