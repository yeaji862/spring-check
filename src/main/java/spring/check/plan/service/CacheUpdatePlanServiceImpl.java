package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CacheUpdatePlanServiceImpl implements UpdatePlanService{
    /**
     *  오늘 날짜, 어제 날짜는 계획 하나당 수정 횟수가 많을 수 있기 때문에 캐싱처리를 하지않지만
     *  그 외에 날짜들의 계획 데이터들은 수정이 거의 없을 것임으로 캐싱처리를 해준다
     *  수정 할 때 마다 @CacheEvict 어노테이션을 통해 로직을 확인하면 비효율적이기 때문에 오늘, 어제 날짜를 제외한 날에만 적용시키기 위해 캐시 전용 클래스를 생성
     */
    private final UpdatePlanServiceImpl updatePlanService;

    @CacheEvict(value = "dailyList", key = "#date")
    @Override
    public int daily(String division, Optional<Integer> seq, int userNum, Optional<String> content) {
        log.info("CacheUpdatePlanServiceImpl.daily()");
        return updatePlanService.daily(division, seq, userNum, content);
    }

    @CacheEvict(value = "habitList", key = "#date")
    @Override
    public int habit(String division, Optional<Integer> seq, int userNum, Optional<String> content) {
        log.info("CacheUpdatePlanServiceImpl.habit()");
        return updatePlanService.habit(division, seq, userNum, content);
    }

    @CacheEvict(value = "dailyList", key = "#date")
    @Override
    public int achievedDaily(String division, int seq) {
        log.info("CacheUpdatePlanServiceImpl.achievedDaily()");
        return achievedDaily(division, seq);
    }

    @CacheEvict(value = "habitList", key = "#date")
    @Override
    public int achievedHabit(String division, int seq) {
        log.info("CacheUpdatePlanServiceImpl.achievedHabit()");
        return achievedHabit(division, seq);
    }
}
