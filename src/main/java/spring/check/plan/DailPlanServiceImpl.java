package spring.check.plan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.check.plan.repository.DailPlanMapper;
import spring.check.plan.service.DailPlanService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DailPlanServiceImpl implements DailPlanService {

    private final DailPlanMapper mapper;
    @Override
    public int upload(DailPlan dailPlan) {
        log.info("DailPlanServiceImpl upload");
        return mapper.upload(dailPlan);
    }

    @Override
    public int editPlan(DailPlan dailPlan) {
        log.info("DailPlanServiceImpl editPlan");
        return mapper.editPlan(dailPlan);
    }

    @Override
    public int deletePlan(int seq, int userNum) {
        log.info("DailPlanServiceImpl deletePlan");
        return mapper.deletePlan(seq, userNum);
    }

    @Override
    public List<DailPlan> planList(String date, int userNum) {
        log.info("DailPlanServiceImpl planList");
        return mapper.planList(date, userNum);
    }

    @Override
    public List<DailPlan> planListByDate(int userNum) {
        log.info("DailPlanServiceImpl planListByDate");
        return mapper.planListByDate(userNum);
    }

    @Override
    public int editComplete(int seq, int userNum, boolean status) {
        log.info("DailPlanServiceImpl editComplete");
        return mapper.editComplete(seq, userNum, status);
    }

    @Override
    public int editPriority(int seq, int userNum, int priority) {
        log.info("DailPlanServiceImpl editPriority");
        return mapper.editPriority(seq, userNum, priority);
    }
}
