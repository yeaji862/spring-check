package spring.check.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.HabitSchedule;
import spring.check.plan.repository.HabitScheduleMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitPlanServiceImpl implements HabitPlanService {

    private final HabitScheduleMapper mapper;
    @Override
    public int upload(HabitSchedule habitSchedule, int day) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.upload(habitSchedule, day);
    }

    @Override
    public int contentEdit(HabitSchedule habitSchedule) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.contentEdit(habitSchedule);
    }

    @Override
    public int statusEdit(int userNum, boolean status, int day) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.statusEdit(userNum, status, day);
    }

    @Override
    public int deletePlan(int seq, int userNum) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.deletePlan(seq, userNum);
    }

    @Override
    public List<HabitSchedule> planListByDate(String date, int userNum) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.planListByDate(date, userNum);
    }

    @Override
    public List<HabitSchedule> planList(int userNum) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.planList(userNum);
    }
}
