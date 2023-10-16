package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import spring.check.plan.repository.UpdateScheduleMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdatePlanServiceImpl implements UpdatePlanService{

    private final UpdateScheduleMapper updateScheduleMapper;

    @Override
    public int daily(String division, Optional<Integer> seq, int userNum, Optional<String> content, Optional<String> date) {
        int num = 0;
        switch (division){
            case "upload" :
                num = updateScheduleMapper.uploadDaily(userNum, content.orElse(""), LocalDate.parse(date.orElse(""), DateTimeFormatter.ofPattern("yyyy.MM.dd"))); break;
            case "edit" :
                num = updateScheduleMapper.editDaily(seq.orElse(0) , content.orElse("")); break;
            case "delete" :
                num = updateScheduleMapper.deleteDaily(seq.orElse(0)); break;
        }

        return num;
    }

    @Override
    public int habit(String division, Optional<Integer> seq, int userNum, Optional<String> content, Optional<String> date) {
        int num = 0;
        switch (division){
            case "upload" :
                num = updateScheduleMapper.uploadHabit(userNum, content.orElse(""), LocalDate.parse(date.orElse(""), DateTimeFormatter.ofPattern("yyyy.MM.dd"))); break;
            case "edit" :
                num = updateScheduleMapper.editHabit(seq.orElse(0), content.orElse("")); break;
            case "delete" :
                num = updateScheduleMapper.deleteHabit(seq.orElse(0)); break;
        }

        return num;
    }
    @Override
    public int achievedDaily(String division, int seq){
        return (division.equals("on")) ? updateScheduleMapper.checkOnDaily(seq) : updateScheduleMapper.checkOffDaily(seq);
    }
    @Override
    public int achievedHabit(String division, int seq, String date){
        return (division.equals("on")) ? updateScheduleMapper.checkOnHabit(seq, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"))) :
                updateScheduleMapper.checkOffHabit(seq, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    }
    @CacheEvict(value = "dailyList", key = "#date")
    public int cacheAchievedDaily(String division, int seq) {
        log.info("CacheUpdatePlanServiceImpl.achievedDaily()");
        return achievedDaily(division, seq);
    }

    @CacheEvict(value = "habitList", key = "#date")
    public int cacheAchievedHabit(String division, int seq, String date) {
        log.info("CacheUpdatePlanServiceImpl.achievedHabit()");
        return achievedHabit(division, seq, date);
    }
}
