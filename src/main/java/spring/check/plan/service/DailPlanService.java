package spring.check.plan.service;

import spring.check.plan.DailSchedule;

import java.util.List;

public interface DailPlanService {

    int upload(DailSchedule dailPlan);
    int editPlan(DailSchedule dailPlan);
    int deletePlan(int seq, int userNum);
    List<DailSchedule> planListByDate(String date, int userNum);
    List<DailSchedule> planList(int userNum);
    int editComplete(int seq, int userNum , boolean status);
    int editPriority(int seq, int userNum , int priority);
}
