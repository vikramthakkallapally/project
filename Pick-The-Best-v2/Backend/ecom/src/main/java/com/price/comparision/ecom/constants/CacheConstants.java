package com.price.comparision.ecom.constants;

import org.springframework.stereotype.Component;

@Component
public class CacheConstants {
	
	public static final Integer localCacheHeapSize = 1000;
	
	public static final Integer localCacheLiveTime = 3600;
	
	public static final String localCacheType = "LRU";
	
	public static final Integer globalCacheHeapSize = 100;
	
	public static final Integer globalCacheLiveTime = 3600;
	
	public static final String globalCacheType = "LRU";

}
