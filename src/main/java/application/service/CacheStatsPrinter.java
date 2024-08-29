package application.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheStatsPrinter {

    @Autowired
    private CacheManager cacheManager;

    public void printCacheStats() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                System.out.println("Cache name: " + cacheName);
                System.out.println("Cache stats: " + cache.getNativeCache().toString());
            }
        });
    }
}
