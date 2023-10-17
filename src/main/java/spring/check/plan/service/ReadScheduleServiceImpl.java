package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.check.plan.*;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.dto.Status;
import spring.check.plan.repository.ReadScheduleMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadScheduleServiceImpl implements ReadScheduleService {
    private final ReadScheduleMapper readScheduleMapper;
    private final ScheduleCalculation scheduleCalculation;
    private final FeedBackServiceImpl feedBackService;

    @Cacheable(value = "infoSchedule", key = "#date.substring(0,7)")
    @Override
    public InfoSchedule infoSchedule(int userNum, String date) {
        log.info("ReadScheduleServiceImpl.infoPlan()");
        InfoSchedule infoSchedule = new InfoSchedule();
        int year = Integer.valueOf(date.substring(0 , 4)); // yyyy.mm.dd
        int month = Integer.valueOf(date.substring(5 , 7));

        List<Status> dail = readScheduleMapper.dailScheduleStatus(userNum, month, year);
        List<Status> habit = readScheduleMapper.habitScheduleStatus(userNum, month, year);

        int[] dailCount = scheduleCalculation.dailyAchievedCount(dail, new int[2]); // [0] = 토탈 카운트 [1] = 달성 카운트
        int habitCount = scheduleCalculation.habitAchievedCount(habit);
        int actualMaximum = scheduleCalculation.getActualMaximum(year, month); // 해당 달의 마지막 day 를 저장해준다

        infoSchedule.setYear(year);
        infoSchedule.setNowMonth(String.format("%02d", month));
        infoSchedule.setDailCount(dailCount[0]);
        infoSchedule.setDailAchievedCount(dailCount[1]);
        infoSchedule.setHabitCount(actualMaximum);
        infoSchedule.setHabitAchievedCount(habitCount);
        infoSchedule.setCalender(scheduleCalculation.setCalendar(dail, habit, new LinkedHashMap<>(), year, month));
        infoSchedule.setAchievedPercent(scheduleCalculation.achievedPercent(dailCount, habitCount, actualMaximum));
        infoSchedule.setAllAchievedCount(readScheduleMapper.allAchievedCount(userNum));

        return infoSchedule;
    }

    @Override
    public HashMap<String, Object> content(int userNum, String date) {
        log.info("ReadScheduleServiceImpl.content()");
        HashMap<String, Object> content = new HashMap<>();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        int month = Integer.valueOf(date.substring(5, 7));
        int year = Integer.valueOf(date.substring(0, 4));
        content.put("daily", readScheduleMapper.dailListByDate(userNum, localDate));
        content.put("habit", readScheduleMapper.habitListByDate(userNum, localDate, month, year));
        content.put("feedBackContent", feedBackService.feedBackContent(userNum, month, year));
        return content;
    }

}
