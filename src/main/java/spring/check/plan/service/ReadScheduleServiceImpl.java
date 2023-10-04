package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.*;
import spring.check.plan.dto.DailSchedule;
import spring.check.plan.dto.HabitSchedule;
import spring.check.plan.dto.InfoSchedule;
import spring.check.plan.dto.Status;
import spring.check.plan.repository.ReadScheduleMapper;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadScheduleServiceImpl implements ReadScheduleService {
    private final ReadScheduleMapper readScheduleMapper;
    private final ScheduleCalculation scheduleCalculation;
    private final FeedBackServiceImpl feedBackService;

    @Override
    public InfoSchedule infoSchedule(int userNum, int month, int year) {
        log.info("ReadScheduleServiceImpl.infoPlan()");
        InfoSchedule infoSchedule = new InfoSchedule();

        try {
            List<Status> dail = readScheduleMapper.dailScheduleStatus(userNum, month, year);
            List<Status> habit = readScheduleMapper.habitScheduleStatus(userNum, month, year);

            infoSchedule.setDailCalendar(scheduleCalculation.setCalendar(dail, new LinkedHashMap<>(), year, month));
            infoSchedule.setHabitCalender(scheduleCalculation.setCalendar(habit, new LinkedHashMap<>(), year, month));

            int[] dailCount = scheduleCalculation.achievedCount(dail, new int[2]); // [0] = 토탈 카운트 [1] = 달성 카운트
            int[] habitCount = scheduleCalculation.achievedCount(habit, new int[2]);

            infoSchedule.setYear(year);
            infoSchedule.setNowMonth(month);
            infoSchedule.setDailCount(dailCount[0]);
            infoSchedule.setDailAchievedCount(dailCount[1]);
            infoSchedule.setHabitCount(habitCount[0]);
            infoSchedule.setHabitAchievedCount(habitCount[1]);
            infoSchedule.setDailCalendar(scheduleCalculation.setCalendar(dail, new LinkedHashMap<>(), year, month));
            infoSchedule.setHabitCalender(scheduleCalculation.setCalendar(habit, new LinkedHashMap<>(), year, month));
            infoSchedule.setAchievedPercent(scheduleCalculation.achievedPercent(dailCount, habitCount));
            infoSchedule.setAllAchievedCount(readScheduleMapper.allAchievedCount(userNum));
        }catch(IndexOutOfBoundsException e){
            log.error(e.getMessage());
        }

        return infoSchedule;
    }

    @Override
    public HashMap<String, Object> content(int userNum, Date date) {
        HashMap<String, Object> content = new HashMap<>();
        content.put("dailContent", readScheduleMapper.dailListByDate(userNum, date));
        content.put("habitContent", readScheduleMapper.habitListByDate(userNum, date));
        content.put("feedBackContent", feedBackService.feedBackContent(userNum, 0, 0));

        return content;
    }

    @Override
    public List<DailSchedule> dailListByDate(int userNum, Date date) {
        return readScheduleMapper.dailListByDate(userNum, date);
    }

    @Override
    public List<HabitSchedule> habitListByDate(int userNum, Date date) {
        return readScheduleMapper.habitListByDate(userNum, date);
    }

}
