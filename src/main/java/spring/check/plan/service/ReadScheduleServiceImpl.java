package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.*;
import spring.check.plan.repository.DailScheduleMapper;
import spring.check.plan.repository.HabitScheduleMapper;
import spring.check.plan.repository.ReadScheduleMapper;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadScheduleServiceImpl implements ReadScheduleService {

    private final DailScheduleMapper dailPlanMapper;
    private final HabitScheduleMapper habitPlanMapper;
    private final ReadScheduleMapper readScheduleMapper;
    private final ScheduleCalculation scheduleCalculation;

    @Override
    public InfoSchedule infoSchedule(int userNum, int month, int year) {
        log.info("ReadScheduleServiceImpl.infoPlan()");
        HashMap<String, LinkedHashMap<Integer, String>> request = new HashMap<>();
        InfoSchedule infoSchedule = new InfoSchedule();

        int actualMaximum = scheduleCalculation.getActualMaximum(year, month);
        List<Status> dail = readScheduleMapper.dailScheduleStatus(userNum, month, year);
        List<Status> habit = readScheduleMapper.habitScheduleStatus(userNum, month, year);

        infoSchedule.setDailCalendar(scheduleCalculation.setCalendar(dail, new LinkedHashMap<>(), year, month, actualMaximum));
        infoSchedule.setHabitCalender(scheduleCalculation.setCalendar(habit, new LinkedHashMap<>(), year, month, actualMaximum));

        return infoSchedule;
    }

    @Override
    public List<DailSchedule> dailListByDate(String date, int userNum) {
        return null;
    }

    @Override
    public List<HabitSchedule> habitListByDate(String date, int userNum) {
        return null;
    }

}
