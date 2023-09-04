package spring.check.plan.service;

import spring.check.plan.DailPlan;

import java.util.List;

public interface DailPlanService {

    int upload(DailPlan dailPlan);
    int editPlan(DailPlan dailPlan);
    int deletePlan(int seq, int userNum);
    List<DailPlan> planList(String date, int userNum);
    List<DailPlan> planListByDate(int userNum);
    int editComplete(int seq, int userNum , boolean status);
    int editPriority(int seq, int userNum , int priority);
}
