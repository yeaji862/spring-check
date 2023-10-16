package spring.check.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleCache {

    @CacheEvict(value = "infoSchedule", key = "#date.substring(0,7)")
    public void infoScheduleCacheDelete(String date){
        log.info("infoScheduleCacheDelete");
        /**
         *   infoSchedule 캐시를 key 별로 삭제
         */
    };

    @CacheEvict(value = "infoSchedule", allEntries = true)
    public void infoScheduleCacheAllDelete(){
        log.info("infoScheduleCacheAllDelete");
        /**
         *   infoSchedule 캐시를 모두 삭제
         */
    };
}
