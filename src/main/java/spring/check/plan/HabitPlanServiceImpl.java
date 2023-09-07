package spring.check.plan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.repository.HabitPlanMapper;
import spring.check.plan.service.HabitPlanService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitPlanServiceImpl implements HabitPlanService {

    private final HabitPlanMapper mapper;
    @Override
    public int upload(HabitPlan habitPlan, int day) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.upload(habitPlan, day);
    }

    @Override
    public int contentEdit(HabitPlan habitPlan) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.contentEdit(habitPlan);
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
    public List<HabitPlan> planListByDate(String date, int userNum) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.planListByDate(date, userNum);
    }

    @Override
    public List<HabitPlan> planList(int userNum) {
        log.info("HabitPlanServiceImpl upload");
        return mapper.planList(userNum);
    }
}
