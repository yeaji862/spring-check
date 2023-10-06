package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.check.plan.*;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.dto.Schedule;
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

    @Cacheable(value = "infoSchedule")
    @Override
    public InfoSchedule infoSchedule(int userNum, String date) {
        log.info("ReadScheduleServiceImpl.infoPlan()");
        InfoSchedule infoSchedule = new InfoSchedule();
        int year = Integer.valueOf(date.substring(0 , 4)); // yyyy.mm.dd
        int month = Integer.valueOf(date.substring(5 , 7));

        List<Status> dail = readScheduleMapper.dailScheduleStatus(userNum, month, year);
        List<Status> habit = readScheduleMapper.habitScheduleStatus(userNum, month, year);

        int[] dailCount = scheduleCalculation.achievedCount(dail, new int[2]); // [0] = 토탈 카운트 [1] = 달성 카운트
        int[] habitCount = scheduleCalculation.achievedCount(habit, new int[2]);

        infoSchedule.setYear(year);
        infoSchedule.setNowMonth(month);
        infoSchedule.setDailCount(dailCount[0]);
        infoSchedule.setDailAchievedCount(dailCount[1]);
        infoSchedule.setHabitCount(habitCount[0]);
        infoSchedule.setHabitAchievedCount(habitCount[1]);
        infoSchedule.setCalender(scheduleCalculation.setCalendar(dail, habit, new LinkedHashMap<>(), year, month));
        infoSchedule.setAchievedPercent(scheduleCalculation.achievedPercent(dailCount, habitCount));
        infoSchedule.setAllAchievedCount(readScheduleMapper.allAchievedCount(userNum));

        return infoSchedule;
    }

    @Override
    public HashMap<String, Object> content(int userNum, String date) {
        log.info("ReadScheduleServiceImpl.content()");
        HashMap<String, Object> content = new HashMap<>();
        content.put("dailyContent", readScheduleMapper.dailListByDate(userNum, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"))));
        content.put("habitContent", readScheduleMapper.habitListByDate(userNum, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"))));
        content.put("feedBackContent", feedBackService.feedBackContent(userNum, Integer.valueOf(date.substring(0 , 4)), Integer.valueOf(date.substring(5 , 7))));

        return content;
    }

    @Cacheable(value = "dailyList", key = "#date")
    @Override
    public List<Schedule> dailyListByDate(int userNum, String date) {
        return readScheduleMapper.dailListByDate(userNum, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    }

    @Cacheable(value = "habitList", key = "#date")
    @Override
    public List<Schedule> habitListByDate(int userNum, String date) {
        return readScheduleMapper.habitListByDate(userNum, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    }

}