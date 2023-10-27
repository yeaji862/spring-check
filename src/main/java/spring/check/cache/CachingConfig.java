package spring.check.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        ConcurrentMapCache infoScheduleCache = new ConcurrentMapCache("infoSchedule", Caffeine.newBuilder()
                .expireAfterWrite(24, TimeUnit.HOURS) // 24시간으로 설정
                .build().asMap(), false);

        cacheManager.setCaches(Arrays.asList(infoScheduleCache));

        return cacheManager;
    }
}
